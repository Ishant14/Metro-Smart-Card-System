package com.ishant.mscs.services.implementation;

import com.ishant.mscs.constant.Constant;
import com.ishant.mscs.entity.Card;
import com.ishant.mscs.entity.Journey;
import com.ishant.mscs.exception.MetroCardException;
import com.ishant.mscs.model.TravelDetail;
import com.ishant.mscs.repository.MetroCardRepository;
import com.ishant.mscs.repository.TravelRepository;
import com.ishant.mscs.services.MetroCardServices;
import com.ishant.mscs.util.JourneyConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetroCardServicesImpl implements MetroCardServices {

    @Autowired
    MetroCardRepository metroCardRepository;
    @Autowired
    TravelRepository travelRepository;
    @Autowired
    FairServicesImpl fairServices;

    @Override
    public long startJoruney(TravelDetail travelDetail) throws MetroCardException {
        Optional<Card> card =  metroCardRepository.findById(travelDetail.getCardNumber());
        if(!card.isPresent()){
            throw new MetroCardException("Card not activated");
        }

        if(!haveMinimumBalance(card)){
             throw new MetroCardException("Card must have minimum balance of "+ Constant.MINIMUM_BALANCE);
        }

        return createJourney(travelDetail);
    }

    @Override
    public Journey endJourney(long journeyId, String destination) throws MetroCardException {
        Optional<Journey> journey = travelRepository.findById(journeyId);
        if(!journey.isPresent()){
            throw new MetroCardException("No entry found for journeyId :" + journeyId);
        }
        Optional<Card> card = metroCardRepository.findById(journey.get().getCardNumber());
        if(!card.isPresent()){
            throw new MetroCardException("No entry found for cardNumber :"+ journey.get().getCardNumber());
        }

        double fair = fairServices.calculateFair(journey.get().getSource(),destination);
        updateCardBalance(card.get(), fair);

        return updateJourney(destination, journey, fair);
    }

    private Journey updateJourney(String destination, Optional<Journey> journey, double fair) {
        Journey updatedJourney = JourneyConvertor.createUpdatedJourney(journey.get(),fair,destination);
        updatedJourney = travelRepository.save(updatedJourney);
        return updatedJourney;
    }


    private void updateCardBalance(Card card, double fair) {
        double currentBalance  = card.getBalance();
        currentBalance = currentBalance - fair;
        card.setBalance(currentBalance);
        metroCardRepository.save(card);
    }


    private boolean haveMinimumBalance(Optional<Card> card) {
        return card.get().getBalance() > Constant.MINIMUM_BALANCE;
    }


    private long createJourney(TravelDetail travelDetail) {
        Journey journey = new Journey();
        journey.setSource(travelDetail.getSourceStation());
        journey.setCardNumber(travelDetail.getCardNumber());
        journey.setStatus("Active");
        Journey journey1 = travelRepository.save(journey);
        return  journey1.getId();
    }

}
