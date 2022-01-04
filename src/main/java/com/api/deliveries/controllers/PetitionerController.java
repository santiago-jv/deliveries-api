package com.api.deliveries.controllers;


import com.api.deliveries.models.Petitioner;
import com.api.deliveries.services.PetitionerService;

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

@RequestMapping("/api/petitioners")
public class PetitionerController {
    @Autowired
    PetitionerService petitionerService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Petitioner> getPetitioner(@PathVariable("id") Long id) {
        Petitioner petitioner = this.petitionerService.getPetitioner(id);
        return petitioner != null ? new ResponseEntity<>(petitioner,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } 
    
    @GetMapping("/delivery/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Petitioner> getPetitionerWithDelivery(@PathVariable("id") Long id) {
        Petitioner petitioner = this.petitionerService.getPetitionerWithDelivery(id);
        return petitioner != null ? new ResponseEntity<>(petitioner,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } 

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Petitioner> savePetitioner(@RequestBody Petitioner petitioner) {
        Petitioner newPetitioner = this.petitionerService.savePetitioner(petitioner);
        return newPetitioner != null ?  new ResponseEntity<>(newPetitioner, HttpStatus.CREATED):new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    } 

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Petitioner> updatePetitioner(@PathVariable("id") Long id,@RequestBody Petitioner petitioner) {
        Petitioner newPetitioner =  this.petitionerService.updatePetitioner(id,petitioner);

        return newPetitioner != null ?  new ResponseEntity<>(newPetitioner, HttpStatus.OK):new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    } 
}