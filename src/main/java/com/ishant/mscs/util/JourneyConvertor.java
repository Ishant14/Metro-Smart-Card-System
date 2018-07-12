package com.ishant.mscs.util;

import com.ishant.mscs.entity.Journey;

public class JourneyConvertor {

    public static Journey createUpdatedJourney(Journey journey,double fair, String destination ){
        Journey updatedJourney = new Journey();
        updatedJourney.setFair(fair);
        updatedJourney.setStatus("Completed");
        updatedJourney.setSource(journey.getSource());
        updatedJourney.setCardNumber(journey.getCardNumber());
        updatedJourney.setId(journey.getId());
        updatedJourney.setDestination(destination);
        return updatedJourney;

    }


}
