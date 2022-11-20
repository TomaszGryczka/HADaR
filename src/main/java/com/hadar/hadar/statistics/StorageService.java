package com.hadar.hadar.statistics;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final BlobServiceClient blobServiceClient;
    private static final String IMAGES_CONTAINER_NAME = "images";
    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    public String saveImageInBlobStorage(final InputStream stream, final String filename) {
        logger.info("Saving " + filename + " in blob storage...");
        final BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(IMAGES_CONTAINER_NAME);
        final BlobClient blobClient = blobContainerClient.getBlobClient(filename);

        blobClient.upload(stream);
        logger.info(filename + " saved in blob storage");

        return blobClient.getBlobUrl();
    }
}
