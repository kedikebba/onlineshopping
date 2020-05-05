package edu.miu.pm.onlineshopping.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.pm.onlineshopping.admin.model.Client;
import edu.miu.pm.onlineshopping.admin.repository.ClientRepository;

@RestController
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@PostMapping("/registerClient")
	public Client saveClient(@RequestBody Client client) {
		clientRepository.save(client);
		
		return client;
	}
	
	@GetMapping("/getClients")
	public List<Client> getClients() {
		
		return clientRepository.findAll();
	}
	
	@GetMapping("/getClient/{clientId}")
	public Client getClient(@PathVariable("clientId") int clientId) {
		Optional<Client> optionalClient=clientRepository.findById(clientId);
		return optionalClient.get();
	}
	
	@PutMapping("/updateClient/{clientId}")
	public Client updateClient(@RequestBody Client client,@PathVariable("clientId") int clientId) {
		Optional<Client> optionalClient=clientRepository.findById(clientId);
		Client updateClient=optionalClient.get();
		
		updateClient.setAddress(client.getAddress());
		updateClient.setFirstName(client.getFirstName());
		updateClient.setLastName(client.getLastName());
		updateClient.setAccount(client.getAccount());
		updateClient.setRole(client.getRole());
		
		clientRepository.save(updateClient);
		
		return updateClient;	
		
	}
	
	@DeleteMapping("/deleteClient/{clientId}")
	public void deleteClient(@PathVariable("clientId") int clientId) {
		clientRepository.deleteById(clientId);
	}
	
	
	
	
	

}
