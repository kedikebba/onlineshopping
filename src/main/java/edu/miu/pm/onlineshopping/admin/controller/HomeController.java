package edu.miu.pm.onlineshopping.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@RequestMapping("home")
	public ModelAndView homePage() {
		ModelAndView mav=new ModelAndView("home");
		return mav;
	}
}
