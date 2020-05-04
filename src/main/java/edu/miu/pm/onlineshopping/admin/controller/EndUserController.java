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
import edu.miu.pm.onlineshopping.admin.model.Status;
import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.repository.EndUserRepository;



@RestController
public class EndUserController {
	
	@Autowired
	private EndUserRepository endUserRepository;
	
	@PostMapping("/registerEndUser")
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
			endUserUpdate.setAccount(endUser.getAccount());
			endUserUpdate.setRole(endUser.getRole());
			
			endUserRepository.save(endUserUpdate);
			
			return endUserUpdate;
		}
	
	@DeleteMapping("/deleteEndUser/{endUserId}")
	public void deleteEndUser(@PathVariable("endUserId") int endUserId) {
		endUserRepository.deleteById(endUserId);
	}
	
	@PutMapping("/activeEndUser")
	public EndUser activateEndUser(@RequestBody EndUser endUser) {
		Optional<EndUser> optionalEndUser=endUserRepository.findById(endUser.getUserId());
		
		EndUser endUserUpdate=optionalEndUser.get();
		
		endUserUpdate.setStatus(Status.ACTIVE);
		
		return endUserUpdate;
	}
	
	@PutMapping("/deactiveEndUser")
	public EndUser deactivateEndUser(@RequestBody EndUser endUser) {
		Optional<EndUser> optionalEndUser=endUserRepository.findById(endUser.getUserId());
		
		EndUser endUserUpdate=optionalEndUser.get();
		
		endUserUpdate.setStatus(Status.INACTIVE);
		
		return endUserUpdate;
	}
	
	/*@GetMapping("/getInactiveEndUsers")
	public List<EndUser> getInactiveEndUsers() {
		return endUserRepository.getInactiveEndUsers();
	}
	
	@GetMapping("/getInactiveEndUser/{vendorId}")
	public EndUser getInactiveEndUser(@PathVariable("vendorId") int vendorId) {
		return endUserRepository.getInactiveEndUser(vendorId);
	}*/
	

}
