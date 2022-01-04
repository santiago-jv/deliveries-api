package com.api.deliveries.security.payload;//tomado de https://www.bezkoder.com/spring-boot-jwt-authentication/

public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
