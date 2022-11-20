package com.hadar.hadar.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.hadar.hadar.properties.AzureStorageAccountProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@ComponentScan
@RequiredArgsConstructor
@EnableConfigurationProperties(AzureStorageAccountProperties.class)
public class AzureStorageAccountConfig {

    private final AzureStorageAccountProperties properties;

    @Bean
    public BlobServiceClient storageClient() {
        System.out.println(properties.getName() + " " + properties.getKey());
        return new BlobServiceClientBuilder()
                .endpoint(endpoint())
                .credential(credential())
                .buildClient();
    }

    private String endpoint() {
        return String.format(Locale.ROOT, "https://%s.blob.core.windows.net", properties.getName());
    }

    private StorageSharedKeyCredential credential() {
        return new StorageSharedKeyCredential(properties.getName(), properties.getKey());
    }
}
