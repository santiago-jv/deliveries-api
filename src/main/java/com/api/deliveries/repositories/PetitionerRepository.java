package com.api.deliveries.repositories;
import java.util.Optional;

import com.api.deliveries.models.Delivery;
import com.api.deliveries.models.Petitioner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionerRepository extends JpaRepository<Petitioner,Long>{
    Optional<Petitioner> findByDelivery(Delivery delivery);
}
