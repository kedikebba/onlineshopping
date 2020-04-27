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

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.repository.VendorRepository;

@RestController
public class VendorController {
	
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
		updateVendor.setPassword(vendor.getPassword());
		updateVendor.setRegistrationFeeStatus(vendor.getRegistrationFeeStatus());
		updateVendor.setRole(vendor.getRole());
		updateVendor.setUserName(vendor.getUserName());
		
		vendorRepository.save(updateVendor);
		
		return updateVendor;
	}
	
	@DeleteMapping("/deleteVendor/{vendorId}")
	public void deleteVendor(@PathVariable("vendorId") int vendorId) {
		vendorRepository.deleteById(vendorId); 
	}

}
