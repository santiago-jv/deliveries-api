package com.api.deliveries.services;
import java.util.List;
import java.util.Optional;

import com.api.deliveries.models.Delivery;
import com.api.deliveries.models.Messenger;
import com.api.deliveries.models.Petitioner;
import com.api.deliveries.models.Receiver;
import com.api.deliveries.repositories.DeliveryPaginate;
import com.api.deliveries.repositories.DeliveryRepository;
import com.api.deliveries.repositories.MessengerRepository;
import com.api.deliveries.repositories.PetitionerRepository;
import com.api.deliveries.repositories.ReceiverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * DeliveryService
 */
@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    MessengerRepository messengerRepository;

    @Autowired
    PetitionerRepository petitionerRepository;

    @Autowired
    ReceiverRepository receiverRepository;
    
    @Autowired
    DeliveryPaginate deliveryPaginate;

    public List<Delivery> getDeliveries() {
        List<Delivery> deliveries = (List<Delivery>) deliveryRepository.findAll();
        return deliveries;
    }

    public List<Delivery> getDeliveriesWithRange(Integer page, Integer limit) {
        List<Delivery> deliveries = deliveryPaginate.findAll(PageRequest.of(page, limit,Sort.by("registDate").ascending())).toList();

        return deliveries;
    }

    public Delivery getDelivery(Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery.isPresent() ? delivery.get(): null;

    }

    public Delivery saveDelivery(Long IdMessenger, long idPetitioner, Long idReceiver, Delivery delivery) {
        try {
        	Optional<Messenger> messenger = messengerRepository.findById(IdMessenger);
        	Optional<Petitioner> petitioner = petitionerRepository.findById(idPetitioner);
            Optional<Receiver> receiver = receiverRepository.findById(idReceiver);

        	if(messenger.isPresent() && petitioner.isPresent() && receiver.isPresent()) {
        		delivery.setMessenger(messenger.get());
                delivery.setPetitioner(petitioner.get());
                delivery.setReceiver(receiver.get());

        		Delivery newDelivery = deliveryRepository.save(delivery);
                petitioner.get().setDelivery(newDelivery);
                receiver.get().setDelivery(newDelivery);
                return newDelivery;
        	}
            return null;
        } catch (Exception error) {
            return null;
        }
    }



    public Delivery updateDelivery(Long id,Long idMessenger, Delivery delivery) {
        Optional<Delivery> currentDelivery = deliveryRepository.findById(id);
        Optional<Messenger> messenger = messengerRepository.findById(idMessenger);
        if (currentDelivery.isPresent()) {
            currentDelivery.get().setState(delivery.getState());
            currentDelivery.get().setDescription(delivery.getDescription());
            currentDelivery.get().setPickUpTime(delivery.getPickUpTime());
            currentDelivery.get().setDeliveryTime(delivery.getDeliveryTime());
            currentDelivery.get().setMessenger(messenger.get());
            
            Delivery newDelivery = deliveryRepository.save(currentDelivery.get());
            
            return newDelivery;
        }
        return null;
    }

    public HttpStatus deleteDelivery(Long id) {
    try {
            deliveryRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception error) {
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }
    }
}