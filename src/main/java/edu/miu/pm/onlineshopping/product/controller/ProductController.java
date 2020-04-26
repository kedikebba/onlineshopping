package edu.miu.pm.onlineshopping.product.controller;

import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping("/products")
//    public String getInventory(Model model){
//        model.addAttribute("products", productService.getAllProducts());
//        return "product_inventory";
//    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getInventory(){
        for(Product produt: productService.getAllProducts())
            System.out.println(produt.getProductName());

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public String searchProduct(@RequestParam String search, Model model){
//        model.addAttribute("products", productService.searchProduct(search));
//
//        return "product_inventory";
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam("search") String search){

        for(Product produt: productService.searchProduct(search))
            System.out.println(produt.getProductName());
        return new ResponseEntity<>(productService.searchProduct(search), HttpStatus.OK);
    }
}
