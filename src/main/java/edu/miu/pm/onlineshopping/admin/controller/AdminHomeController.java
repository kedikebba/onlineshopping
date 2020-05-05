package edu.miu.pm.onlineshopping.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminHomeController {
	
	@RequestMapping("/home")
	public ModelAndView homePage() {
		ModelAndView mav=new ModelAndView("home");
		return mav;
	}
}
