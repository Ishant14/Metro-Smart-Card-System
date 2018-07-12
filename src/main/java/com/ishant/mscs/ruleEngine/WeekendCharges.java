package com.ishant.mscs.ruleEngine;

import com.ishant.mscs.constant.Constant;

public class WeekendCharges implements CalculateCharges {

    @Override
    public double calculateCharges(int numberofStationTravelled) {
         return Constant.WEEKEND_CHARGES * numberofStationTravelled;
    }
}
