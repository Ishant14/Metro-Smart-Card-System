package com.ishant.mscs.controller;

import com.ishant.mscs.entity.Journey;
import com.ishant.mscs.model.TravelDetail;
import com.ishant.mscs.services.implementation.MetroCardServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class MetroCardController {

    @Autowired
    MetroCardServicesImpl metroCardServicesImpl;

    @PostMapping("/mscs/swipein")
    public ResponseEntity swipeIn(@RequestBody TravelDetail travelDetail)  {
        long travelId;
        try {
            travelId = metroCardServicesImpl.startJoruney(travelDetail);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),INTERNAL_SERVER_ERROR);
        }
            return new ResponseEntity(travelId, OK);
     }

     @PostMapping("mscs/swipeout/{journeyId}/{destination}")
    public ResponseEntity swipeOut(@PathVariable long journeyId, @PathVariable String destination){
         Journey journey;
        try {
            journey = metroCardServicesImpl.endJourney(journeyId, destination);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(journey.getId(),OK);
     }



}

