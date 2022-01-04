
package com.api.deliveries.repositories;

import com.api.deliveries.models.Delivery;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPaginate extends PagingAndSortingRepository<Delivery, Long>{
    
    
}