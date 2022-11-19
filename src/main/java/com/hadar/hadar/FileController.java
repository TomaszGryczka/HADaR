package com.hadar.hadar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {
    private static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PutMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestParam("file") MultipartFile multipartFile,
                                              @RequestParam("hour") String hour) throws IOException {
        logger.info(multipartFile.getOriginalFilename());
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        logger.info(filename);
        Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
        logger.info(fileStorage.toString());
        copy(multipartFile.getInputStream(), fileStorage, REPLACE_EXISTING);
        return ResponseEntity.ok().body(filename);
    }
}
