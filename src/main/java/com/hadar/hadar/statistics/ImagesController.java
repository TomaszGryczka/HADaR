package com.hadar.hadar.statistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ImagesController {



    private final StatisticsService statisticsService;

    @PutMapping("/upload")
    public ResponseEntity<String> uploadImageAndGenerateStatistics(@RequestParam("file") MultipartFile multipartFile,
                                               @RequestParam("hour") String hour) throws IOException {

        statisticsService.uploadImageAndGenerateStatistics(multipartFile, hour);


        return ResponseEntity.ok().body("ok?");
    }

    @PostMapping("/{projectId}/classify/iterations/{publishedName}/url")
    public void siema(@PathVariable String projectId, @PathVariable String publishedName) {
        System.out.println("siema");
    }
}
