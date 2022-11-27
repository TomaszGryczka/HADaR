package com.hadar.hadar.statistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadar.hadar.CategoryChartData;
import com.hadar.hadar.properties.AzureCustomVisionProperties;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.CustomVisionPredictionClient;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.ClassifyImageUrlOptionalParameter;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.DetectImageUrlOptionalParameter;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.ImagePrediction;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.Prediction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.joda.time.Hours;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final StorageService storageService;
    private final CustomVisionPredictionClient predictor;
    private final CustomVisionPredictionClient predictorDetection;
    private final AzureCustomVisionProperties customVisionProperties;
    private static final Double PROBABILITY_THRESHOLD = 0.7;
    private static final Double DETECTION_PROBABILITY_THRESHOLD = 0.6;
    private static final List<String> hours
            = Arrays.asList("18", "19", "20", "21", "22", "23", "0", "1", "2", "3", "4");
    HoursActionsCount hac = new HoursActionsCount();

    @Getter
    private final Map<String, CategoryChartData> categories = Map.of(
            "calling", new CategoryChartData(hac),
            "dancing", new CategoryChartData(hac),
            "drinking", new CategoryChartData(hac),
            "eating", new CategoryChartData(hac),
            "fighting", new CategoryChartData(hac),
            "hugging", new CategoryChartData(hac)
    );

    @Getter
    private Map<String, Integer> people= new HashMap<>();
    @Getter
    private Map<String, Double> finalPeople = new HashMap<>();

    @Getter
    private Map<String, Integer> food= new HashMap<>();
    @Getter
    private Map<String, Double> finalFood= new HashMap<>();

    @Getter
    private Map<String, Integer> drink= new HashMap<>();
    @Getter
    private Map<String, Double> finalDrink= new HashMap<>();

    @Getter
    private Map<String, Integer> imagesPerHour = new HashMap<>();


    public String uploadImageAndGenerateStatistics(final MultipartFile multipartFile, String hour) {
        final String filename = multipartFile.getOriginalFilename();
        String uploadedImageUrl;



        hour = hour.replaceFirst("^0+(?!$)", "");

        if (imagesPerHour.get(hour) == null) {
            imagesPerHour.put(hour, 1);
        } else {
            imagesPerHour.put(hour, imagesPerHour.get(hour) + 1);
        }

        logger.info("Got file: " + filename + " at " + hour);

        try {
            uploadedImageUrl = storageService.saveImageInBlobStorage(multipartFile.getInputStream(), filename);
        } catch (IOException e) {
            logger.error("Failed to save" + filename + "in blob storage");
            throw new RuntimeException(e);
        }
        logger.info(uploadedImageUrl);

        final ImagePrediction result = predictor.predictions()
                .classifyImageUrl(
                        UUID.fromString(customVisionProperties.getProjectUuid()),
                        "Iteration9",
                        uploadedImageUrl,
                        new ClassifyImageUrlOptionalParameter()
                );

        logger.info("Logging predictions:");
        result.predictions().forEach(prediction ->
                logger.info(prediction.tagName() + " -> " + prediction.probability()));

        final ImagePrediction detectionResult = predictorDetection.predictions()
                .detectImageUrl(
                        UUID.fromString(customVisionProperties.getProjectDetectionUuid()),
                        "Iteration5",
                        uploadedImageUrl,
                        new DetectImageUrlOptionalParameter()
                );

        logger.info("Detection predictions: ");
        String finalHour = hour;
        detectionResult.predictions().forEach(prediction ->
                {
                    if(prediction.probability()>DETECTION_PROBABILITY_THRESHOLD) {
                        logger.info(prediction.tagName() + " -> " + prediction.probability());

                        switch(prediction.tagName()){
                            case "person":
                                people.put(finalHour, people.get(finalHour)!=null ? people.get(finalHour)+1:1);
                                break;
                            case "food":
                                food.put(finalHour, food.get(finalHour)!=null ? food.get(finalHour)+1:1);
                                break;
                            case "drink":
                                drink.put(finalHour, drink.get(finalHour)!=null ? drink.get(finalHour)+1:1);
                                break;
                            default:
                        }
                    }

                }
        );

        logger.info("Final people: " + finalPeople.toString() + "\npeople: "+people.toString()+
                "\nimagesPerHour: "+imagesPerHour.toString());

        finalPeople.put(hour, (people.get(hour)/(double)imagesPerHour.get(hour)));

        for(String h:hours){
            finalPeople.putIfAbsent(h, 0.0);
            food.putIfAbsent(h,0);
            drink.putIfAbsent(h,0);
        }

        if(finalPeople.get(hour) <=0){
            finalFood.put(hour,(double)(food.get(hour)));
            finalDrink.put(hour,(double)(drink.get(hour)));
        }else{

            finalFood.put(hour, (food.get(hour)/finalPeople.get(hour)));
            finalDrink.put(hour, (drink.get(hour))/finalPeople.get(hour));

            logger.info("Final people: " + finalPeople.toString() + "\nfood: "+food.toString()+
                    "\ndrink: "+drink.toString());
        }

        for(String h:hours){
            finalFood.putIfAbsent(h, 0.0);
            finalDrink.putIfAbsent(h, 0.0);
        }

        addPredictionsToCategoryStatistics(result.predictions(), hour);

        return uploadedImageUrl;
    }

    private void addPredictionsToCategoryStatistics(final List<Prediction> predictions, final String hour) {
        predictions.forEach(prediction -> {
            if (prediction.probability() > PROBABILITY_THRESHOLD) {
                hac.addActionToHour(hour);
                categories.get(prediction.tagName()).addActionToAverage(hour);

                categories.forEach((cat,obj) ->{
                    if(!cat.equals(prediction.tagName()))
                        obj.refreshActionStatistics();
                });
            }
        });
    }

    // to test only
    public void logChartData() {
        try {
            logger.debug(objectMapper.writeValueAsString(categories));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
