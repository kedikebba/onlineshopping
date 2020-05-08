package edu.miu.pm.onlineshopping.admin.controller;

import java.util.List;
import java.util.Optional;

import edu.miu.pm.onlineshopping.admin.model.*;
import edu.miu.pm.onlineshopping.admin.repository.AccountRepository;
import edu.miu.pm.onlineshopping.admin.service.AddressService;
import edu.miu.pm.onlineshopping.admin.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.miu.pm.onlineshopping.admin.repository.VendorRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class AdminVendorController {
	
	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AddressService addressService;


	//------------Added by Getaneh--------------------------//

	@GetMapping("/vendor/register")
	public ModelAndView getEndUserRegisterForm() {

		Account account = new Account();
		Address address = new Address();
		Vendor newVendor =  new Vendor();
		newVendor.setAccount(account);
		newVendor.setAddress(address);

		ModelAndView mav = new ModelAndView("vendor_registration_form");
		mav.addObject("newVendor", newVendor);

		return mav;
	}

	@PostMapping("/activate/{vendorId}")
	public RedirectView activateVendor(@PathVariable("vendorId") int id){
		Vendor vendor = vendorService.getVendorById(id);
		vendor.setStatus(Status.ACTIVE);
		vendor = vendorService.saveVendor(vendor);

		RedirectView rv = new RedirectView();
		rv.setUrl("admin");

		return rv;
	}
	@PostMapping("/deactivate/{vendorId}")
	public RedirectView deactivateVendor(@PathVariable("vendorId") int id){
		Vendor vendor = vendorService.getVendorById(id);
		vendor.setStatus(Status.INACTIVE);
		vendor = vendorService.saveVendor(vendor);

		RedirectView rv = new RedirectView();
		rv.setUrl("admin");

		return rv;
	}

	//------------End of Added by Getaneh--------------------------//
	
	@PostMapping("/registerVendor")
	public RedirectView registerVendor(@ModelAttribute("newVendor") Vendor vendor) {
		accountRepository.save(vendor.getAccount());
		Address address = null;
		if (vendor.getAddress() != null) {
			address = addressService.getAddress(vendor.getAddress().getStreet(), vendor.getAddress().getState(),
					vendor.getAddress().getCity(), vendor.getAddress().getZipCode());
		}
		if (address != null){
			vendor.setAddress(address);
		}
		else if (vendor.getAddress() != null){
			addressService.saveAddress(vendor.getAddress());
		}
		vendorRepository.save(vendor);

		return new RedirectView("home");

	}
	
	@GetMapping("/getVendors")
	public List<Vendor> getVendors() {
		return vendorRepository.findAll();
	}
	
	@GetMapping("/getVendor/{vendorId}")
	public Vendor getVendor(@PathVariable("vendorId") int vendorId) {
		Optional<Vendor> optionalVendor=vendorRepository.findById(vendorId);
		
		return optionalVendor.get();
	}
	
	@PutMapping("/updateVendor/{vendorId}")
	public Vendor updateVendor(@RequestBody Vendor vendor,@PathVariable("vendorId") int vendorId) {
		Optional<Vendor> optionalVendor=vendorRepository.findById(vendorId);
		
		Vendor updateVendor=optionalVendor.get();
		
		updateVendor.setAddress(vendor.getAddress());
		updateVendor.setFirstName(vendor.getFirstName());
		updateVendor.setLastName(vendor.getLastName());
		updateVendor.setAccount(vendor.getAccount());
		updateVendor.setRegistrationFeeStatus(vendor.getRegistrationFeeStatus());
		updateVendor.setRole(vendor.getRole());
		
		vendorRepository.save(updateVendor);
		
		return updateVendor;
	}
	
	@DeleteMapping("/deleteVendor/{vendorId}")
	public void deleteVendor(@PathVariable("vendorId") int vendorId) {
		vendorRepository.deleteById(vendorId); 
	}
	
	@PutMapping("/approveVendor")
	public String approveVendor(@RequestBody Vendor vendor) {
		if(vendor.getRegistrationFeeStatus()) {
			Optional<Vendor> optionalVendor=vendorRepository.findById(vendor.getVendorId());
			
			Vendor updateVendor=optionalVendor.get();
			updateVendor.setStatus(Status.ACTIVE);
			return "Approved";
			
		}
		
		return "Not approved";
	}
	
	@PutMapping("/rejectVendor")
	public Vendor rejectVendor(@RequestBody Vendor vendor) {
		
			Optional<Vendor> optionalVendor=vendorRepository.findById(vendor.getVendorId());
			
			Vendor updateVendor=optionalVendor.get();
			updateVendor.setStatus(Status.INACTIVE);
			
			return updateVendor;
	}
	
	@GetMapping("/getInactiveVendors")
	public List<Vendor> getInactiveVendors() {
		Status status=Status.INACTIVE;
		return vendorRepository.findAllByStatus(status);
	}
	
	@GetMapping("/getInactiveVendor/{vendorId}")
	public Vendor getInactiveVendor(@PathVariable("vendorId") int vendorId) {
		Status status=Status.INACTIVE;
		return vendorRepository.findByVendorIdAndStatus(vendorId,status);
	}
	
	@GetMapping("/getActiveVendors")
	public List<Vendor> getActiveVendors() {
		Status status=Status.ACTIVE;
		return vendorRepository.findAllByStatus(status);
	}
	
	@GetMapping("/getActiveVendor/{vendorId}")
	public Vendor getActiveVendor(@PathVariable("vendorId") int vendorId) {
		Status status=Status.ACTIVE;
		return vendorRepository.findByVendorIdAndStatus(vendorId,status);
	}

}
