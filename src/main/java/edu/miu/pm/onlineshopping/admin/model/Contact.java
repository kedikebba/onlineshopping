package edu.miu.pm.onlineshopping.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contact {
	
	@Id
	private int contactId;
	
	private String phoneNumber;
	private String email;
	
	public Contact() {
		super();
	}

	public Contact(int contactId, String phoneNumber, String email) {
		super();
		this.contactId = contactId;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
	
	
	
	
	

}
