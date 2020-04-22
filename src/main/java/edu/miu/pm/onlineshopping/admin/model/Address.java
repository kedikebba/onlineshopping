package edu.miu.pm.onlineshopping.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	private int addressId;
	
	private String state;
	private String city;
	private String zipCode;
	
	public Address() {
		super();
	}

	public Address(int addressId, String state, String city, String zipCode) {
		super();
		this.addressId = addressId;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", state=" + state + ", city=" + city + ", zipCode=" + zipCode + "]";
	}
	
	

}
