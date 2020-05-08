package edu.miu.pm.onlineshopping.admin.controller;

import java.util.List;
import java.util.Optional;

import edu.miu.pm.onlineshopping.admin.model.*;
import edu.miu.pm.onlineshopping.admin.repository.AccountRepository;
import edu.miu.pm.onlineshopping.admin.service.AddressService;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.miu.pm.onlineshopping.admin.repository.EndUserRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class EndUserController {
	
	@Autowired
	private EndUserRepository endUserRepository;
	@Autowired
	private EndUserService endUserService;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AddressService addressService;

	//------------Added by Getaneh--------------------------//

	@GetMapping("/register")
	public ModelAndView getEndUserRegisterForm() {
		Account account = new Account();
		Address address = new Address();
		EndUser newUser =  new EndUser();
		newUser.setAccount(account);
		newUser.setAddress(address);
		ModelAndView mav = new ModelAndView("user_registration_form");
		mav.addObject("newUser", newUser);

		return mav;
	}
	@PostMapping("/activate-user/{userId}")
	public RedirectView activateUser(@PathVariable("userId") int id){
		EndUser user = endUserService.getEndUserbyId(id);
		user.setStatus(Status.ACTIVE);
		endUserService.saveUser(user);

		RedirectView rv = new RedirectView();
		rv.setUrl("admin");

		return rv;
	}
	@PostMapping("/deactivate-user/{userId}")
	public RedirectView deactivateVendor(@PathVariable("userId") int id){
		EndUser user = endUserService.getEndUserbyId(id);
		user.setStatus(Status.INACTIVE);
		endUserService.saveUser(user);

		RedirectView rv = new RedirectView();
		rv.setUrl("admin");

		return rv;
	}

	//------------End of Added by Getaneh--------------------------//

	
	@PostMapping("/registerEndUser")
	public RedirectView saveEndUser(@ModelAttribute("newUser") EndUser endUser) {
		accountRepository.save(endUser.getAccount());
		Address address = null;
		if (endUser.getAddress() != null) {
			address = addressService.getAddress(endUser.getAddress().getStreet(), endUser.getAddress().getState(),
					endUser.getAddress().getCity(), endUser.getAddress().getZipCode());
		}
		if (address != null){
			endUser.setAddress(address);
		}
		else if (endUser.getAddress() != null){
			addressService.saveAddress(endUser.getAddress());
		}

		endUserRepository.save(endUser);

		return new RedirectView("home");
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
