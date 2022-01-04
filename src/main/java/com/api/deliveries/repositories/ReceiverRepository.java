package com.api.deliveries.repositories;
import java.util.Optional;

import com.api.deliveries.models.Delivery;
import com.api.deliveries.models.Receiver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver,Long>{
    Optional<Receiver> findByDelivery(Delivery delivery);

}
