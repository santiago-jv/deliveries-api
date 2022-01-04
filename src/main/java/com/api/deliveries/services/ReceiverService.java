package com.api.deliveries.services;

import java.util.Optional;

import com.api.deliveries.models.Delivery;
import com.api.deliveries.models.Receiver;
import com.api.deliveries.repositories.DeliveryRepository;
import com.api.deliveries.repositories.ReceiverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
    @Autowired
    ReceiverRepository receiverRepository;
    
    @Autowired
    DeliveryRepository deliveryRepository;
    public Receiver getReceiver(Long id) {
        Optional<Receiver> receiver = receiverRepository.findById(id);
        if(receiver.isPresent())
            return receiver.get();
        else 
            return null;    
    }
    public Receiver getReceiverWithDelivery(Long id){
        Optional<Delivery> delivery = this.deliveryRepository.findById(id);
        if (delivery.isPresent()){

            Optional<Receiver> petitioner =this.receiverRepository.findByDelivery(delivery.get());

            return petitioner.isPresent() ? petitioner.get():null ;
            
        }
        return null;
        
    }
    public Receiver saveReceiver(Receiver receiver) {
        try {
            Receiver newReceiver = receiverRepository.save(receiver);
            return newReceiver;
        }
        catch(Exception error){
            return null;
        }
    }

    public Receiver updateReceiver (Long id, Receiver receiver){
        Optional<Receiver> currentReceiver = receiverRepository.findById(id);

        if(currentReceiver.isPresent()) {
            currentReceiver.get().setName(receiver.getName());
            currentReceiver.get().setAddress(receiver.getAddress());
            currentReceiver.get().setNumberCell(receiver.getNumberCell());

            return this.receiverRepository.save(currentReceiver.get());

        }
        return null;

    }
}   
