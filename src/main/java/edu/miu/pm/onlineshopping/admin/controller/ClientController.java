package edu.miu.pm.onlineshopping.admin.controller;

import java.util.List;
import java.util.Optional;

import edu.miu.pm.onlineshopping.admin.model.Account;
import edu.miu.pm.onlineshopping.admin.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.miu.pm.onlineshopping.admin.model.Client;
import edu.miu.pm.onlineshopping.admin.repository.ClientRepository;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	

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


	@GetMapping("/registerClient")
	public ModelAndView registerVendorP() {
		ModelAndView mav=new ModelAndView("clientregist");
		return mav;
	}

	@PostMapping("/registerClient")
	@ResponseBody
	public Client registerVendor(@RequestParam String user_name,
								 @RequestParam String passowrd) {


		Client client = new Client();
		client.setAccount(new Account(user_name,passowrd));
		clientRepository.save(client);
		return client;
	}
	
	

}
