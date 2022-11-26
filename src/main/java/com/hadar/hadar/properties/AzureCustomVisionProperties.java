package com.hadar.hadar.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@ConfigurationProperties(prefix = "azure.custom.vision")
public class AzureCustomVisionProperties {

    @NotBlank
    private String predictionEndpoint;
    @NotBlank
    private String predictionKey;
    @NotBlank
    private String projectUuid;

    @NotBlank
    private String predictionDetectionEndpoint;
    @NotBlank
    private String predictionDetectionKey;
    @NotBlank
    private String projectDetectionUuid;
}
