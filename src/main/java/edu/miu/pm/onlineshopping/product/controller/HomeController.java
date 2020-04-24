package edu.miu.pm.onlineshopping.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/product")
public class HomeController {

    @GetMapping("/home")
    public String getHome(){

        return "redirect:/products";
    }
}
