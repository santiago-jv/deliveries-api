package com.api.deliveries.controllers;

import com.api.deliveries.models.Receiver;
import com.api.deliveries.services.ReceiverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000",} )
@RequestMapping("/api/receivers")
public class ReceiverController {

    @Autowired
    ReceiverService receiverService;
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Receiver> getReceiver(@PathVariable("id") Long id) {
        Receiver receiver = this.receiverService.getReceiver(id);

        return receiver != null ? new ResponseEntity<>(receiver,HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }

    @GetMapping("/delivery/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Receiver> getReceiverWithDelivery(@PathVariable("id") Long id) {
        Receiver receiver = this.receiverService.getReceiverWithDelivery(id);
        return receiver != null ? new ResponseEntity<>(receiver,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } 

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Receiver> saveReceiver(@RequestBody Receiver receiver) {
        Receiver newReceiver = this.receiverService.saveReceiver(receiver);

        return newReceiver != null ? new ResponseEntity<>(newReceiver,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } 

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Receiver> updateReceiver(@PathVariable("id") Long id,@RequestBody Receiver receiver) {
        Receiver newReceiver = this.receiverService.updateReceiver(id, receiver);

        return newReceiver != null ? new ResponseEntity<>(newReceiver,HttpStatus.OK): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } 

}