package edu.miu.pm.onlineshopping.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EndUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String firstName;
	private String lastName;
	
	@OneToOne(cascade= {CascadeType.ALL})
	private Account account;
	
	private Role role=Role.ENDUSER;
	
	@OneToOne(cascade= {CascadeType.ALL})
	private Address address;
	
	private Status status=Status.ACTIVE;
	
	public EndUser() {
		super();
	}

	public EndUser(String firstName, String lastName, Account account, Role role, Address address,Status status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
		this.role = role;
		this.address = address;
		this.status = status;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EndUser [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", account="
				+ account + ", role=" + role + ", address=" + address + ", status=" + status + "]";
	}
	

}
