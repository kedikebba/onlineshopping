package edu.miu.pm.onlineshopping.admin.model;

import edu.miu.pm.onlineshopping.product.model.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vendorId;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	@OneToOne(cascade= {CascadeType.ALL})
	private Address address;
	
	private boolean registrationFeeStatus;
	private Role role=Role.VENDOR;

	
	public Vendor() {
		super();
	}

	public int getVendorId() {
		return vendorId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean getRegistrationFeeStatus() {
		return registrationFeeStatus;
	}

	public void setRegistrationFeeStatus(boolean registrationFeeStatus) {
		this.registrationFeeStatus = registrationFeeStatus;
	}

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", address=" + address + ", registrationFeeStatus="
				+ registrationFeeStatus + ", role=" + role + "]";
	}
	
	


}
