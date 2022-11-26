package com.hadar.hadar.config;

import com.hadar.hadar.properties.AzureCustomVisionProperties;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.CustomVisionPredictionClient;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.CustomVisionPredictionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@RequiredArgsConstructor
@EnableConfigurationProperties(AzureCustomVisionProperties.class)
public class AzureCustomVisionConfig {

    private final AzureCustomVisionProperties properties;

    @Bean
    public CustomVisionPredictionClient predictor() {
        return CustomVisionPredictionManager
                .authenticate(properties.getPredictionEndpoint(), properties.getPredictionKey())
                .withEndpoint(properties.getPredictionEndpoint());
    }

    @Bean
    public CustomVisionPredictionClient predictorDetection() {
        return CustomVisionPredictionManager
                .authenticate(properties.getPredictionDetectionEndpoint(), properties.getPredictionDetectionKey())
                .withEndpoint(properties.getPredictionDetectionEndpoint());
    }


}
