package com.ishant.mscs.services;

import com.ishant.mscs.entity.Journey;
import com.ishant.mscs.exception.MetroCardException;
import com.ishant.mscs.model.TravelDetail;

public interface MetroCardServices {

    public long startJoruney(TravelDetail travelDetail) throws MetroCardException;

    public Journey endJourney(long journeyId, String destination) throws MetroCardException;

}
