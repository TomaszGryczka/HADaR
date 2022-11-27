package com.hadar.hadar.statistics;

import com.hadar.hadar.CategoryChartData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/food-drink")
@CrossOrigin(origins = "https://hadar-statistics.azurewebsites.net")
@RequiredArgsConstructor
public class FoodDrinkController {

    private final StatisticsService statisticsService;
    private static final List<String> hours
            = Arrays.asList("18", "19", "20", "21", "22", "23", "0", "1", "2", "3", "4");

    @GetMapping
    public List<Map<String,Double>> getFoodDrinkStatitics() {

        List<Map<String,Double>> returnVal = new ArrayList<>();

        Map<String,Double> returnFood = statisticsService.getFinalFood();
        Map<String,Double> returnDrink =statisticsService.getFinalDrink();

        for(String h:hours){
            returnFood.putIfAbsent(h, 0.0);
            returnDrink.putIfAbsent(h,0.0);
        }

        returnVal.add(returnFood);
        returnVal.add(returnDrink);

        return returnVal;
    }

}
