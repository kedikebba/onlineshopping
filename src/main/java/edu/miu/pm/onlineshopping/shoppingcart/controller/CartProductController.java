package edu.miu.pm.onlineshopping.shoppingcart.controller;

import edu.miu.pm.onlineshopping.product.model.Product;

import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
//@RestController
@RequestMapping("products/api/v1")
public class CartProductController {

    @Autowired
    private OrderService orderService;


        @GetMapping("/list")
    public String getInventory(Model model){
        model.addAttribute("products", orderService.getAllProducts());
        return "product_inventory";
    }
//    @GetMapping("/list")
//    public ResponseEntity<List<Product>> getInventory(){
//
//        return new ResponseEntity<>(orderService.getAllProducts(), HttpStatus.OK);
//    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String search, Model model){
        model.addAttribute("products", orderService.searchProduct(search));

        return "product_inventory";
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Product>> searchProduct(@RequestParam("search") String search){
//
//        return new ResponseEntity<>(orderService.searchProduct(search), HttpStatus.OK);
//    }
}
