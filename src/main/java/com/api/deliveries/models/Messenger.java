package com.api.deliveries.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="messengers")
public class Messenger {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column
	private String name;

	@Column
	private String address;

	@Column
	private Character gender;
	
	@Column(name="identification_card")
	private String identificationCard;
	
	@Column(name="number_cell")
	private String numberCell;
	
	@Column(name="motorcycle_plate" )
	private String motorcyclePlate;
	
	@OneToMany(mappedBy="messenger",cascade=CascadeType.ALL)
	private List<Delivery> deliveries; 
		
	public Messenger() {
		super();
	}
	
	public Character getGender() {
		return this.gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	
	public List<Delivery> getDeliveries() {
		return this.deliveries;
	}
	public void setDeliveries(List<Delivery> deliveries){
		this.deliveries = deliveries;
	}
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentificationCard() {
		return identificationCard;
	}

	public void setIdentificationCard(String identificationCard) {
		this.identificationCard = identificationCard;
	}

	public String getNumberCell() {
		return numberCell;
	}

	public void setNumberCell(String numberCell) {
		this.numberCell = numberCell;
	}

	public String getMotorcyclePlate() {
		return motorcyclePlate;
	}

	public void setMotorcyclePlate(String motorcyclePlate) {
		this.motorcyclePlate = motorcyclePlate;
	}
	
}
