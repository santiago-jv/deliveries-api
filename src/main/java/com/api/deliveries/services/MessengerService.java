package com.api.deliveries.services;

import java.util.List;
import java.util.Optional;

import com.api.deliveries.models.Delivery;
import com.api.deliveries.models.Messenger;
import com.api.deliveries.repositories.DeliveryRepository;
import com.api.deliveries.repositories.MessengerPaginate;
import com.api.deliveries.repositories.MessengerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MessengerService {
    @Autowired
    MessengerRepository messengerRepository;
    
    @Autowired
    MessengerPaginate messengerPaginate;

    @Autowired
    DeliveryRepository deliveryRepository;

    public List<Messenger> getMessengers() {
        List<Messenger> messengers = (List<Messenger>) messengerRepository.findAll();
        return messengers;
    }

    public List<Messenger> getMessengersWithRange(Integer page, Integer limit) {
        List<Messenger> messengers = messengerPaginate.findAll(PageRequest.of(page, limit,Sort.by("name").ascending())).toList();   
        return messengers;
    }
    public Messenger getMessengerWithDelivery (Long id) {
        Optional<Delivery> delivery = this.deliveryRepository.findById(id);
        if(delivery.isPresent()){
            List<Messenger> messengers = (List<Messenger>) this.messengerRepository.findAll();
            for (Messenger messenger : messengers) {
                if(messenger.getDeliveries().contains(delivery.get())){
                    return messenger;
                }
            }
        }
        
        return null;
    }

    public Messenger getMessenger(Long id) {
        Optional<Messenger> messenger = messengerRepository.findById(id);
        return messenger.isPresent() ? messenger.get(): null;
    }

    public Messenger saveMessenger(Messenger messenger) {
        try{
            Messenger newMessenger = messengerRepository.save(messenger);
            return newMessenger;
        }catch(Exception error){
            return null;
        }
    
    }
    public Messenger updateMessenger( Long id, Messenger messenger){
		Optional<Messenger> currentMessengerContainer = messengerRepository.findById(id);

        if(currentMessengerContainer.isPresent()){
            Messenger currentMessenger = currentMessengerContainer.get();
            currentMessenger.setName(messenger.getName());
            currentMessenger.setAddress(messenger.getAddress());
            currentMessenger.setIdentificationCard(messenger.getIdentificationCard());
            currentMessenger.setMotorcyclePlate(messenger.getMotorcyclePlate());
            currentMessenger.setNumberCell(messenger.getNumberCell());
            currentMessenger.setGender(messenger.getGender());

            Messenger newMessenger = messengerRepository.save(currentMessenger);
            return newMessenger;
        }
        return null;
	}

    public HttpStatus deleteMessenger(Long id) {
        try{

            messengerRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        }
        catch(Exception error) {
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }
    }
}