package com.hadar.hadar.statistics;

import com.hadar.hadar.CategoryChartData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class StatisticsController {

    private final static Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    private final StatisticsService statisticsService;

    @GetMapping
    public Map<String, CategoryChartData> getStatistics() {
        statisticsService.logChartData();

        return statisticsService.getCategories();
    }

}
