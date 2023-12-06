package com.example.AptItSolutions.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OurLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    private String address;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "OurLocations [id=" + id + ", city=" + city + ", address=" + address + "]";
	}
	public OurLocations(long id, String city, String address) {
		super();
		this.id = id;
		this.city = city;
		this.address = address;
	}
	public OurLocations() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}

