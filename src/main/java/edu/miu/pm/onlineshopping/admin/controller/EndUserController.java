package edu.miu.pm.onlineshopping.admin.controller;

import java.util.List;
import java.util.Optional;

import edu.miu.pm.onlineshopping.admin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.miu.pm.onlineshopping.admin.repository.EndUserRepository;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class EndUserController {
	
	@Autowired
	private EndUserRepository endUserRepository;

	@GetMapping("/registerEndUser")
	public ModelAndView registerVendorP() {
		ModelAndView mav=new ModelAndView("enduserregist");
		return mav;
	}
	
	@PostMapping("/registerEndUser")
	public EndUser saveEndUser(@RequestParam String user_name,
							   @RequestParam String passowrd) {


		EndUser endUser = new EndUser();
		endUser.setAccount(new Account(user_name,passowrd));
		endUserRepository.save(endUser);
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
	
	@GetMapping("/getInactiveEndUsers")
	public List<EndUser> getInactiveEndUsers() {
		Status status=Status.INACTIVE;
		return endUserRepository.findAllByStatus(status);
	}
	
	@GetMapping("/getInactiveEndUser/{userId}")
	public EndUser getInactiveEndUser(@PathVariable("userId") int userId) {
		Status status=Status.INACTIVE;
		return endUserRepository.findByUserIdAndStatus(userId, status);
	}
	
	@GetMapping("/getActiveEndUsers")
	public List<EndUser> getActiveEndUsers() {
		Status status=Status.ACTIVE;
		return endUserRepository.findAllByStatus(status);
	}
	
	@GetMapping("/getActiveEndUser/{userId}")
	public EndUser getActiveEndUser(@PathVariable("userId") int userId) {
		Status status=Status.ACTIVE;
		return endUserRepository.findByUserIdAndStatus(userId, status);
	}
	
	

}
