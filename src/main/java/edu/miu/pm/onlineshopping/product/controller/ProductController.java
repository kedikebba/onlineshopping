package edu.miu.pm.onlineshopping.product.controller;


import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.service.VendorService;
import edu.miu.pm.onlineshopping.product.model.Category;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.service.ICategoryService;
import edu.miu.pm.onlineshopping.product.service.Imp.CategoryService;
import edu.miu.pm.onlineshopping.product.service.Imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

////////////////     Contributor:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private VendorService vendorService;


    @GetMapping(value = "/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    //---------------------------- Added by Getaneh --------------------------//

    @GetMapping(value = {"/vendor", "/delete/vendor", "/addProduct/vendor", "/editProduct/vendor"})
    public ModelAndView getVendorHomePage(){
        Vendor vendor = vendorService.getVendorByName("Mary");
        ModelAndView mav = new ModelAndView();
        mav.addObject("products", productService.getVendorProducts(vendor));
        mav.addObject("vendor", vendor);
        mav.setViewName("vendor_product_list");
        return mav;
    }

    @GetMapping("/addProduct")
    public ModelAndView displayAddProductForm(){

        ModelAndView mav = new ModelAndView();
        mav.addObject("newProduct", new Product());
        List<Category> categories = productService.findAll().stream()
                                                .map(product -> product.getCategory())
                                                .distinct()
                                                .collect(Collectors.toList());
        mav.addObject("categories", categories);
        Vendor vendor = vendorService.getVendorByName("Mary");
        mav.addObject("vendor", vendor);
        mav.setViewName("add_product_form");
        return mav;
    }

    @PostMapping("/addProduct")
    public RedirectView addProduct(@ModelAttribute("newProduct") Product newProduct) throws IOException {
        Category category = categoryService.getCategoryByName(newProduct.getCategory().getCategoryName());
        if (category == null){
            categoryService.save(newProduct.getCategory());
        } else {
            newProduct.setCategory(category);
        }


        Product productInStock = productService.getByProductNameAndCategory(newProduct.getProductName(), newProduct.getCategory().getCategoryName());
        if (productInStock != null){
            productInStock.setQuantity(productInStock.getQuantity() + newProduct.getQuantity());

            productService.saveProduct(productInStock);
        } else {
            productService.saveProduct(newProduct);
        }

        return new RedirectView("vendor");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") long id) {

        ModelAndView mav = new ModelAndView();
        Product product = productService.findById(id);
        mav.addObject("product", product);
        Vendor vendor = vendorService.getVendorByName("Mary");
        mav.addObject("vendor", vendor);
        List<Category> categories = productService.findAll().stream()
                .map(prod -> prod.getCategory())
                .distinct()
                .collect(Collectors.toList());
        mav.addObject("categories", categories);
        mav.setViewName("edit_product");

        return mav;
    }

    @PostMapping(value = "/editProduct")
    public RedirectView editProduct(@ModelAttribute("product") Product product) throws IOException {

        productService.saveProduct(product);

        return new RedirectView("vendor");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteProduct(@PathVariable(name = "id") long id) {
        productService.deleteProductById(id);

        return new RedirectView("vendor");
    }

    //----------------------------End of Added by Getaneh --------------------------//

//    @GetMapping(value = "/productbyvendor")
//    public List<Product> getProductByVendor(Principal principal) {
//        return productService.findByVendor(userService.loadUserByUsername(principal.getName()));
//    }
////
//    @GetMapping(value = "/approved_products")
//    public List<Product> getApprovedProduct(@RequestHeader Map<String, String> header) {
//        return  productService.getApprovedProduct();
//    }

    @GetMapping(value = "/product/{id}")
    public Product getProductById(@PathVariable("id") Long productId) {
        return productService.findById(productId);
    }

//    @PostMapping(value = "/product", produces = "application/json")
//    public Product save(@RequestBody ProductRequest product, Principal principal) {
//
//       // User user = implementation.loadUserByUsername(principal.getName());
//        Product product1 = new Product(categoryService.findById(product.getCategoryId()));
//        product.updateProduct(product1);
//        //  product1.setUser(user); //vendor of the product
//       // product1.setStatus("unapproved");
//       // productService.save(product1, user);
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
