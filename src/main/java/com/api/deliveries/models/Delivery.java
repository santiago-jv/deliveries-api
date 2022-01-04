package com.api.deliveries.models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
@Table(name="deliveries")
public class Delivery {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@Column
	private Boolean state = false;
	
	@Column(name = "regist_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP )
    private Date registDate = new Date(System.currentTimeMillis()) ;
	
	@Column
	private String description;
	
	@Column(name="pick_up_time")
	private String pickUpTime;
	
	@Column(name="delivery_time")
	private String deliveryTime;
	
	@ManyToOne
    @JoinColumn(name = "id_messenger")
	@JsonIgnore
	private Messenger messenger;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_petitioner")
	@JsonIgnore
	private Petitioner petitioner; 
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_receiver")
	@JsonIgnore
	private Receiver receiver; 
	

	public Date getRegistDate() {
		return this.registDate;
	}
	public String getPickUpTime() {
		return this.pickUpTime;
	}
	
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public String getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	public Receiver getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	
	public Petitioner getPetitioner() {
		return this.petitioner;
	}

	public void setPetitioner(Petitioner petitioner) {
		this.petitioner = petitioner;
	}
	public Long getId() {
		return this.Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}

	public Boolean isState() {
		return this.state;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Messenger getMessenger() {
		return this.messenger;
	}

	public void setMessenger(Messenger messenger) {
		this.messenger = messenger;
	}

	public Delivery() {
		super();
	}
}