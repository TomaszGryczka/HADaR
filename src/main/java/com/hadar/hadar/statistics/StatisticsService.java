package com.hadar.hadar.statistics;

import com.hadar.hadar.properties.AzureCustomVisionProperties;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.CustomVisionPredictionClient;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.ClassifyImageUrlOptionalParameter;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.ImagePrediction;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);
    private final StorageService storageService;
    private final CustomVisionPredictionClient predictor;
    private final AzureCustomVisionProperties customVisionProperties;

    public void uploadImageAndGenerateStatistics(final MultipartFile multipartFile, final String hour) {
        final String filename = multipartFile.getOriginalFilename();
        String uploadedImageUrl;

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
    }
}
