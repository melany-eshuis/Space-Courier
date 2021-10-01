package com.space.SpaceCourier.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class SpaceCourier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String brand;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
	this.brand=brand;
	}
}

