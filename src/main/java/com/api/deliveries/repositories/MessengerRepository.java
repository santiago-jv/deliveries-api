
package com.api.deliveries.repositories;


import com.api.deliveries.models.Messenger;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessengerRepository extends PagingAndSortingRepository<Messenger, Long>{
    
}