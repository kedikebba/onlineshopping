package edu.miu.pm.onlineshopping.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EndUser {
	
	@Id
	private int userId;
	
	private String firstName;
	private String lastName;
	private Contact contact;
	private Address address;
	public EndUser() {
		super();
	}
	public EndUser(int userId, String firstName, String lastName, Contact contact, Address address) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.address = address;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", contact=" + contact
				+ ", address=" + address + "]";
	}
	
	

}
