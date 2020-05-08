package edu.miu.pm.onlineshopping.admin.controller;

import edu.miu.pm.onlineshopping.admin.model.Client;
import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.service.AdminService;
import edu.miu.pm.onlineshopping.admin.service.ClientService;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import edu.miu.pm.onlineshopping.admin.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@RequestMapping("/admin")
public class AdminHomeController {

	@Autowired
	private EndUserService endUserService;
	@Autowired
	VendorService vendorService;
	@Autowired
	ClientService clientService;
	
	@RequestMapping(value = {"/admin", "/activate/admin","/activate-user/admin", "/deactivate/admin", "/deactivate-user/admin"})
	public ModelAndView homePage() {
		List<EndUser> endUsers = endUserService.getAllEndUsers();
		List<Vendor> vendors = vendorService.getAllVendors();
		List<Client> clients = clientService.getAllClients();

		ModelAndView mav= new ModelAndView("users_list");
		mav.addObject("endUsers", endUsers);
		mav.addObject("vendors", vendors);
		mav.addObject("clients", clients);
		return mav;
	}
}
