package com.hadar.hadar;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CategoryChartData {

    private static final List<String> hours
            = Arrays.asList("18", "19", "20", "21", "22", "23", "0", "1", "2", "3", "4");
    private final HashMap<String, Integer> actionsPerHour;
    @Getter
    private final HashMap<String, Double> averageActionsPerHour;
    private int sumOfActions;

    public CategoryChartData() {
        actionsPerHour = new HashMap<>();
        averageActionsPerHour = new HashMap<>();
        hours.forEach(hour -> {
            averageActionsPerHour.put(hour, 0.0);
            actionsPerHour.put(hour, 0);
        });
        sumOfActions = 0;
    }

    public void addActionToAverage(String actionHour) {
        sumOfActions += 1;
        final int prevNumOfActionsInHour = actionsPerHour.get(actionHour);
        final int currentNumOfActionsInHour = prevNumOfActionsInHour + 1;
        actionsPerHour.put(actionHour, currentNumOfActionsInHour);
        hours.forEach(hour -> {
            final double average = Double.valueOf(actionsPerHour.get(hour)) / sumOfActions;
            averageActionsPerHour.put(hour, average);
        });
    }
}
