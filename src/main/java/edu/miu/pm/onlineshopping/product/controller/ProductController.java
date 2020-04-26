package edu.miu.pm.onlineshopping.product.controller;

import edu.miu.pm.onlineshopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getInventory(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "product_inventory";
    }
}
