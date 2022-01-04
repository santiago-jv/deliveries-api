package com.api.deliveries.controllers;

import java.util.List;

import com.api.deliveries.models.Messenger;
import com.api.deliveries.services.MessengerService;

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

@RestController
@CrossOrigin(origins = {"http://localhost:3000"} )
@RequestMapping("/api/messengers")
public class MessengerController {

	@Autowired
	MessengerService messengerService;
	@GetMapping()
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Messenger>> getMessengers() {
		List<Messenger> messengers = this.messengerService.getMessengers();
		return messengers.isEmpty() ? new ResponseEntity<>(messengers,HttpStatus.NO_CONTENT): new ResponseEntity<>(messengers,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Messenger> getMessenger(@PathVariable("id") Long id) {
		Messenger messenger = this.messengerService.getMessenger(id);
		return messenger == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND): new ResponseEntity<>(messenger,HttpStatus.OK);
	}
	@GetMapping("/delivery/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Messenger> getMessengerWithDelivery(@PathVariable("id") Long  id) {
		Messenger messenger = this.messengerService.getMessengerWithDelivery(id);
		return messenger == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND): new ResponseEntity<>(messenger,HttpStatus.OK);
	}

	@GetMapping("/query")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Messenger>> getMessengerWithRange(@RequestParam("page") String page,
			@RequestParam("limit") String limit) {

		List<Messenger> messengers = this.messengerService.getMessengersWithRange(Integer.parseInt(page), Integer.parseInt(limit));
		return !messengers.isEmpty()? new ResponseEntity<>(messengers,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Messenger> saveMessenger(@RequestBody Messenger messenger) {
		Messenger newMessenger = this.messengerService.saveMessenger(messenger);
		return newMessenger == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(newMessenger,HttpStatus.CREATED);	
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Messenger> updateMessenger(@PathVariable("id") Long id, @RequestBody Messenger messenger){

		Messenger newMessenger = this.messengerService.updateMessenger(id, messenger);
		return newMessenger == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR):new ResponseEntity<>(newMessenger,HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<HttpStatus> deleteMessenger(@PathVariable("id") Long id) {
		return new ResponseEntity<>(this.messengerService.deleteMessenger(id));
	}

}