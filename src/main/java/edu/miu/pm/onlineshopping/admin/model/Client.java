package edu.miu.pm.onlineshopping.admin.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	
	private String firstName;
	private String lastName;
	
	@OneToOne(cascade= {CascadeType.ALL})
	private Account account;
	
	private Role role;
	
	@OneToOne(cascade= {CascadeType.ALL})
	private Address address;
	
	public Client() {
		super();
	}

	public Client(String firstName, String lastName, Account account, Role role, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
		this.role = role;
		this.address = address;
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

	public int getClientId() {
		return clientId;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", firstName=" + firstName + ", lastName=" + lastName + ", account="
				+ account + ", role=" + role + ", address=" + address + "]";
	}


}
