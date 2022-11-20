package com.hadar.hadar.statistics;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class StatisticsController {

    private final static Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @GetMapping
    public ResponseEntity<String> getStatistics() {
        return ResponseEntity.ok().body("");
    }

}
