package com.hadar.hadar.statistics;

import com.hadar.hadar.CategoryChartData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins = "https://hadar-statistics.azurewebsites.net")
@RequiredArgsConstructor
public class PeopleStatisticsController {

    private final StatisticsService statisticsService;
    private static final List<String> hours
            = Arrays.asList("18", "19", "20", "21", "22", "23", "0", "1", "2", "3", "4");

    @GetMapping
    public Map<String,Double> getPeopleStatitics() {

        Map<String,Double> returnVal;

        returnVal = statisticsService.getFinalPeople();

        for(String h:hours){
            returnVal.putIfAbsent(h, 0.0);
        }

        return returnVal;
    }

}
