package edu.miu.pm.onlineshopping.product.controller;


import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.service.VendorService;
import edu.miu.pm.onlineshopping.product.model.Category;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.model.ProductDetail;
import edu.miu.pm.onlineshopping.product.service.ICategoryService;
import edu.miu.pm.onlineshopping.product.service.Imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ICategoryService categoryService;
//    @Autowired
//    private VendorService vendorService;

    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    // ************************************Waiting for VendorService Implementation **************************************
//    @GetMapping(value = "/productbyvendor")
//    public List<Product> getProductByVendor(Principal principal) {
//        return productService.findAllByVendor(vendorService.loadVendorByUsername(principal.getName()));
//
//    }

    @GetMapping(value = "/approved_products")
    public List<Product> getApprovedProduct(@RequestHeader Map<String, String> header) {
        return productService.getApprovedProduct();
    }

    @GetMapping(value = "/product/{id}")
    public Product getProductById(@PathVariable("id") Long productId) {
        return productService.findById(productId);
    }

    // ************************************Waiting for VendorService Implementation **************************************

    //    @PostMapping(value = "/product", produces = "application/json")
//    public Product save(@RequestBody ProductDetail product, Principal principal) {
//
//        Vendor vendor = vendorService.loadVendorByUsername(principal.getName());
//        Product product1 = new Product(categoryService.findById(product.getCategoryId()));
//        product.updateProduct(product1);
//          product1.setVendor(vendor); //vendor of the product
//        product1.setStatus("unapproved");
//        productService.save(product1, vendor);
//
//        return product1;
//    }
    @DeleteMapping(value = "/product/{id}")
    public Product delete(@PathVariable("id") Long productId) {
        Product product = productService.findById(productId);
        productService.delete(productId);
        return product;
    }

    @GetMapping("/products/search")
    public List<Product> search(@RequestParam("productNumber") String productNumber, @RequestParam("productName") String productName,
                                @RequestParam("minProductPrice") Double minProductPrice, @RequestParam("maxProductPrice")
                                        Double maxProductPrice, @RequestParam("status") Integer status) {
        boolean byProductNumber = productNumber != null && !productNumber.isEmpty();
        boolean byProductName = productName != null && !productName.isEmpty();
        boolean byMinPrice = minProductPrice != 0;
        boolean byMaxPrice = maxProductPrice != 0;
        boolean byStatus = status == 1;

        if (byProductNumber && byProductName && (byMinPrice || byMaxPrice)) {
            return productService.findByAll(productNumber, productName, minProductPrice, maxProductPrice, status);
        } else if (byProductNumber && byProductName) {
            return productService.findByNameNumber(productNumber, productName, status);
        } else if (byProductName && (byMinPrice || byMaxPrice)) {
            return productService.findByNamePrice(productName, minProductPrice, maxProductPrice, status);
        } else if (byProductNumber && (byMinPrice || byMaxPrice)) {
            return productService.findByNumberPrice(productNumber, minProductPrice, maxProductPrice, status);
        } else if (byProductName) {
            return productService.findByName(productName, status);
        } else if (byProductNumber) {
            return productService.findByNumber(productNumber, status);
        } else if ((byMinPrice || byMaxPrice)) {
            return productService.findByPrice(minProductPrice, maxProductPrice, status);
        } else if (byStatus) {
            return productService.findByStatus(status);
        } else
            return productService.findAll();

    }


}