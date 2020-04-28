package edu.miu.pm.onlineshopping.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vendor {
	@Id
	private int vendorId;
	
	private String name;
	private Address address;
	private Contact contact;
	private Role role;
	
	public Vendor() {
		super();
	}
	

	public Vendor(int vendorId, String name, Address address, Contact contact, Role role) {
		super();
		this.vendorId = vendorId;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.role = role;
	}





	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", name=" + name + ", address=" + address + ", contact=" + contact
				+ "]";
	}
	
	
	

}
