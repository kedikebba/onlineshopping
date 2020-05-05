package edu.miu.pm.onlineshopping.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vendorId;
	
	private String firstName;
	private String lastName;
	
	@OneToOne(cascade= {CascadeType.ALL})
	private Account account;
	
	@OneToOne(cascade= {CascadeType.ALL})
	private Address address;
	
	private boolean registrationFeeStatus;
	private Role role=Role.VENDOR;
	private Status status;
	
	public Vendor() {
		super();
	}
	
	public Vendor(String firstName, String lastName, Account account, Address address, boolean registrationFeeStatus,
			Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
		this.address = address;
		this.registrationFeeStatus = registrationFeeStatus;
		this.role = role;
		this.status=Status.INACTIVE;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public int getVendorId() {
		return vendorId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", firstName=" + firstName + ", lastName=" + lastName + ", account="
				+ account + ", address=" + address + ", registrationFeeStatus=" + registrationFeeStatus + ", role="
				+ role + "]";
	}


}
