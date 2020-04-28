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
	private String userName;
	private String password;
	private Role role=Role.ENDUSER;
	
	@OneToOne(cascade= {CascadeType.ALL})
	private Address address;
	
	public EndUser() {
		super();
	}
	
	public EndUser(String firstName, String lastName, String userName, String password, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
	}

	public int getUserId() {
		return userId;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "EndUser [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", role=" + role + ", address=" + address + "]";
	}
	

}
