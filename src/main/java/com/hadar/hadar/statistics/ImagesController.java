package com.hadar.hadar.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ImagesController {


    private final StatisticsService statisticsService;

    @PutMapping("/upload")
    public String uploadImageAndGenerateStatistics(@RequestParam("file") MultipartFile multipartFile,
                                                   @RequestParam("hour") String hour) {

        return statisticsService.uploadImageAndGenerateStatistics(multipartFile, hour);
    }
}
