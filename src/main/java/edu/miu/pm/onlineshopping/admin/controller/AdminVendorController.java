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

import edu.miu.pm.onlineshopping.admin.model.Status;
import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.repository.VendorRepository;

@RestController
public class AdminVendorController {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@PostMapping("/registerVendor")
	public Vendor registerVendor(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
		
		return vendor;
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
		return vendorRepository.getInactiveEndUsers();
	}
	
	@GetMapping("/getInactiveVendor/{vendorId}")
	public Vendor getInactiveVendor(@PathVariable("vendorId") int vendorId) {
		return vendorRepository.getInactiveVendor(vendorId);
	}

}
