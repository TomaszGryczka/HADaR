package com.hadar.hadar;

import com.hadar.hadar.statistics.HoursActionsCount;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CategoryChartData {

    private static final List<String> hours
            = Arrays.asList("18", "19", "20", "21", "22", "23", "0", "1", "2", "3", "4");
    private final HashMap<String, Integer> actionsPerHour;
    @Getter
    private final HashMap<String, Double> averageActionsPerHour;
    HoursActionsCount hac;

    public CategoryChartData( HoursActionsCount hac) {
        this.hac = hac;

        actionsPerHour = new HashMap<>();
        averageActionsPerHour = new HashMap<>();
        hours.forEach(hour -> {
            averageActionsPerHour.put(hour, 0.0);
            actionsPerHour.put(hour, 0);
        });

    }

    public void addActionToAverage(String actionHour) {

        int prevNumOfActions=actionsPerHour.get(actionHour);
        int currNumOfActions=prevNumOfActions+1;
        actionsPerHour.put(actionHour,currNumOfActions);

        hours.forEach(hour -> {
            final double average = Double.valueOf(actionsPerHour.get(hour)) / hac.getAllActionsInHour(hour);
            averageActionsPerHour.put(hour, average);
        });
    };

    public void refreshActionStatistics(){
        hours.forEach(hour -> {
            final double average = Double.valueOf(actionsPerHour.get(hour)) / hac.getAllActionsInHour(hour);
            averageActionsPerHour.put(hour, average);
        });
    }

}

