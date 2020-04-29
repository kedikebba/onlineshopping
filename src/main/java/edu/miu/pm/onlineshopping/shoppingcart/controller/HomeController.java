package edu.miu.pm.onlineshopping.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class HomeController {

    @GetMapping("/home")
    public String getHome(){

        return "redirect:/products/api/v1/list";
    }
}
