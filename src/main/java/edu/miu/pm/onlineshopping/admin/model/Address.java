package edu.miu.pm.onlineshopping.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

////////////////     Contributor:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String street;
	private String state;
	private String city;
	private String zipCode;
	private String email;
	private String phoneNumber;
	
	public Address() {
		super();
	}

	

	public Address(String state, String city, String zipCode, String email, String phoneNumber) {
		super();
		
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj.getClass() != this.getClass())
			return false;
		Address address = (Address) obj;
		return (address.getStreet().equals(this.getStreet()) &&
				address.getState().equals(this.getState()) &&
				address.getCity().equals(this.getCity()) &&
				address.getZipCode().equals(this.getZipCode()));
	}


	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", state=" + state + ", city=" + city + ", zipCode=" + zipCode
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
}
