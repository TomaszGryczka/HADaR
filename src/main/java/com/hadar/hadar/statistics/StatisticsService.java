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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final StorageService storageService;
    private final CustomVisionPredictionClient predictor;
    private final CustomVisionPredictionClient predictorDetection;
    private final AzureCustomVisionProperties customVisionProperties;
    private static final Double PROBABILITY_THRESHOLD = 0.7;
    private static final Double DETECTION_PROBABILITY_THRESHOLD = 0.6;
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

    public String uploadImageAndGenerateStatistics(final MultipartFile multipartFile, String hour) {
        final String filename = multipartFile.getOriginalFilename();
        String uploadedImageUrl;

        hour = hour.replaceFirst("^0+(?!$)", "");
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
                        "Iteration4",
                        uploadedImageUrl,
                        new DetectImageUrlOptionalParameter()
                );

        logger.info("Detection predictions: ");
        detectionResult.predictions().forEach(prediction ->
                {
                    if(prediction.probability()>DETECTION_PROBABILITY_THRESHOLD) {
                        logger.info(prediction.tagName() + " -> " + prediction.probability());
                    }

                }
        );


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
