package com.api.deliveries.services;

import java.util.Optional;
import com.api.deliveries.models.Delivery;
import com.api.deliveries.models.Petitioner;
import com.api.deliveries.repositories.DeliveryRepository;
import com.api.deliveries.repositories.PetitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetitionerService {
    @Autowired
    PetitionerRepository petitionerRepository;
    @Autowired
    DeliveryRepository deliveryRepository;

    public Petitioner getPetitioner(Long id){

        Optional<Petitioner> petitioner = this.petitionerRepository.findById(id);
        if(petitioner.isPresent()) {
            return petitioner.get();
        }
        else{
            return null;
        }
    }
    public Petitioner getPetitionerWithDelivery(Long id){
        Optional<Delivery> delivery = this.deliveryRepository.findById(id);
        if (delivery.isPresent() ){

            Optional<Petitioner> petitioner =this.petitionerRepository.findByDelivery(delivery.get());

            return petitioner.isPresent() ? petitioner.get():null ;
            
        }
  
        return null;
        
    }

    public Petitioner savePetitioner(Petitioner petitioner) {
        try {
            Petitioner newPetitioner = petitionerRepository.save(petitioner);
            return newPetitioner;
        } 
        catch (Exception error) {
            return null;
        }
    }

    public Petitioner updatePetitioner(Long id, Petitioner petitioner) {
        Optional<Petitioner> currentPetitioner = petitionerRepository.findById(id);

        if (currentPetitioner.isPresent()) {
            currentPetitioner.get().setName(petitioner.getName());
            currentPetitioner.get().setAddress(petitioner.getAddress());
            currentPetitioner.get().setNumberCell(petitioner.getNumberCell());
            
            Petitioner newPetitioner = petitionerRepository.save(currentPetitioner.get());
            return newPetitioner;
        }
        return null;
    }

}
