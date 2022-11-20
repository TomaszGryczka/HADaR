package com.hadar.hadar.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "azure.storage.account")
public class AzureStorageAccountProperties {
    private String name;
    private String key;
}
