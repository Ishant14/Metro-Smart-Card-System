package com.ishant.mscs.services.implementation;

import com.ishant.mscs.ruleEngine.CalculateCharges;
import com.ishant.mscs.ruleEngine.WeekDayCharges;
import com.ishant.mscs.ruleEngine.WeekendCharges;
import com.ishant.mscs.services.FairServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FairServicesImpl implements FairServices {

    @Value("#{'${station.list}'.split(',')}")
    private List<String> stationList;

    @Override
    public double calculateFair(String source, String destination) {

        Map<Integer, CalculateCharges> map = getCalculateChargesMap();
        int dayofWeek = findDayOfWeek();
        CalculateCharges calculateChargesAlgo = map.get(dayofWeek);
        return  calculateChargesAlgo.calculateCharges(calculateNumberOfStationTravelled(source, destination));
    }

    private Map<Integer, CalculateCharges> getCalculateChargesMap() {
        WeekDayCharges weekDayCharges = new WeekDayCharges();
        WeekendCharges weekendCharges = new WeekendCharges();
        Map<Integer,CalculateCharges> map = new HashMap();
        map.put(1,weekDayCharges);
        map.put(2,weekDayCharges);
        map.put(3,weekDayCharges);
        map.put(4,weekDayCharges);
        map.put(5,weekDayCharges);
        map.put(6,weekendCharges);
        map.put(7,weekendCharges);
        return map;
    }


    private int findDayOfWeek(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 2);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    private int calculateNumberOfStationTravelled(String source, String destination){
        int sourceIndex = -1;
        int destinationIndex= -1;
        int count =1;
        for(String station: stationList){
            if(station.equalsIgnoreCase(source)){
                sourceIndex = count;
            }
            if(station.equalsIgnoreCase(destination)){
                destinationIndex = count;
            }
            count++;
        }
        int totalNumberOfstation = destinationIndex - sourceIndex;
        return totalNumberOfstation > 0 ? totalNumberOfstation : -(totalNumberOfstation);
    }

}
