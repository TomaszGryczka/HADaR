package com.hadar.hadar.statistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadar.hadar.CategoryChartData;
import com.hadar.hadar.properties.AzureCustomVisionProperties;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.CustomVisionPredictionClient;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.ClassifyImageUrlOptionalParameter;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.ImagePrediction;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.Prediction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final AzureCustomVisionProperties customVisionProperties;
    private static final Double PROBABILITY_THRESHOLD = 0.7;

    @Getter
    private final Map<String, CategoryChartData> categories = Map.of(
            "calling", new CategoryChartData(),
            "dancing", new CategoryChartData(),
            "drinking", new CategoryChartData(),
            "eating", new CategoryChartData(),
            "fighting", new CategoryChartData(),
            "hugging", new CategoryChartData()
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
        logger.debug(uploadedImageUrl);

        final ImagePrediction result = predictor.predictions()
                .classifyImageUrl(
                        UUID.fromString(customVisionProperties.getProjectUuid()),
                        "Iteration9",
                        uploadedImageUrl,
                        new ClassifyImageUrlOptionalParameter()
                );

        logger.debug("Logging predictions:");
        result.predictions().forEach(prediction ->
                logger.debug(prediction.tagName() + " -> " + prediction.probability()));

        addPredictionsToCategoryStatistics(result.predictions(), hour);

        return uploadedImageUrl;
    }

    private void addPredictionsToCategoryStatistics(final List<Prediction> predictions, final String hour) {
        predictions.forEach(prediction -> {
            if (prediction.probability() > PROBABILITY_THRESHOLD) {
                categories.get(prediction.tagName()).addActionToAverage(hour);
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
