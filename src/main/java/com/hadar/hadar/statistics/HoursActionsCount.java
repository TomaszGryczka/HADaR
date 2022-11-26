package com.hadar.hadar.statistics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HoursActionsCount {

    private List<String> hours;
    private HashMap<String, Integer> hoursActionsAmount;

    public HoursActionsCount(){
        hours = Arrays.asList("18", "19", "20", "21", "22", "23", "0", "1", "2", "3", "4");
        hoursActionsAmount=new HashMap<>();
        hours.forEach(hour -> {
            hoursActionsAmount.put(hour,0);
        });
    }

    public void addActionToHour(String hour){
        this.hoursActionsAmount.put(hour,this.hoursActionsAmount.get(hour)+1);
    }

    public int getAllActionsInHour(String hour){
        return hoursActionsAmount.get(hour);
    }

}
