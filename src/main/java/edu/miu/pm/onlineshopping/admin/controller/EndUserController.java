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

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.repository.EndUserRepository;



@RestController
public class EndUserController {
	
	@Autowired
	private EndUserRepository endUserRepository;
	
	@PostMapping("/registerEnduser")
	public EndUser saveEndUser(@RequestBody EndUser endUser) {
		endUserRepository.save(endUser);
		return endUser;
	}
	
	@GetMapping("/getEndusers")
	public List<EndUser> getEndUsers() {
		return endUserRepository.findAll();
	}
	
	@GetMapping("/getEnduser/{endUserId}") 
	public EndUser getEndUser(@PathVariable("endUserId") int endUserId){
		
		Optional<EndUser> optionalEndUser=endUserRepository.findById(endUserId);
		return optionalEndUser.get();
	}
	
	@PutMapping("/updateEnduser/{endUserId}") 
		public EndUser updateEndUser(@RequestBody EndUser endUser,@PathVariable("endUserId") int endUserId) {
			Optional<EndUser> optionalEndUser=endUserRepository.findById(endUserId);
			
			EndUser endUserUpdate=optionalEndUser.get();
			
			endUserUpdate.setAddress(endUser.getAddress());
			endUserUpdate.setFirstName(endUser.getFirstName());
			endUserUpdate.setLastName(endUser.getLastName());
			endUserUpdate.setPassword(endUser.getPassword());
			endUserUpdate.setRole(endUser.getRole());
			endUserUpdate.setUserName(endUser.getUserName());
			
			endUserRepository.save(endUserUpdate);
			
			return endUserUpdate;
		}
	
	@DeleteMapping("/deleteEndUser/{endUserId}")
	public void deleteEndUser(@PathVariable("endUserId") int endUserId) {
		endUserRepository.deleteById(endUserId);
	}
	

}
