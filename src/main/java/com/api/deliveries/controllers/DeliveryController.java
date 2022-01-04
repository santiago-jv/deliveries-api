package com.api.deliveries.controllers;

import java.util.List;

import com.api.deliveries.models.Delivery;
import com.api.deliveries.services.DeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * DeliveryController
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000",} )
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<Delivery>> getDeliveries() {
        List<Delivery> deliveries = this.deliveryService.getDeliveries();

        return !deliveries.isEmpty() ? new ResponseEntity<>(deliveries, HttpStatus.OK):new ResponseEntity<>( deliveries,HttpStatus.NO_CONTENT);
    
    }
    
    @GetMapping("/query")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Delivery>> getDeliveriesWithRange(@RequestParam("page") String page,@RequestParam("limit") String limit) {
        List<Delivery> deliveries = this.deliveryService.getDeliveriesWithRange(Integer.parseInt(page),Integer.parseInt(limit)) ;
        return !deliveries.isEmpty() ? new ResponseEntity<>(deliveries, HttpStatus.OK):new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Delivery> getDelivery(@PathVariable("id") Long id) {
        Delivery delivery = this.deliveryService.getDelivery(id);

        return delivery != null ? new ResponseEntity<>(delivery, HttpStatus.OK):new ResponseEntity<>( HttpStatus.NOT_FOUND);
    
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/messenger/{idMessenger}/petitioner/{idPetitioner}/receiver/{idReceiver}")
    public ResponseEntity<Delivery> saveDelivery(@PathVariable("idMessenger") Long idMessenger, @PathVariable("idPetitioner") Long idPetitioner, @PathVariable("idReceiver") Long idReceiver,@RequestBody Delivery delivery){
    	Delivery newDelivery = this.deliveryService.saveDelivery(idMessenger,idPetitioner,idReceiver,delivery);

        return newDelivery != null ? new ResponseEntity<>(newDelivery, HttpStatus.CREATED):new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("/{id}/messenger/{idMessenger}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable("id") Long id,@PathVariable("idMessenger") Long idMessenger, @RequestBody Delivery delivery){
        Delivery newDelivery = this.deliveryService.updateDelivery(id,idMessenger,delivery);

        return newDelivery != null ? new ResponseEntity<>(newDelivery, HttpStatus.OK):new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDelivery(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.deliveryService.deleteDelivery(id));
    }

}