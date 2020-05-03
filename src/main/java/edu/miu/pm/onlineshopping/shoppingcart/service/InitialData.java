package edu.miu.pm.onlineshopping.shoppingcart.service;


import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.service.VendorService;
import edu.miu.pm.onlineshopping.product.model.Category;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.service.ICategoryService;
import edu.miu.pm.onlineshopping.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class InitialData implements ApplicationRunner {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        loadData();
        System.out.println("Loading Initial data");

    }

    private void loadData(){
        Vendor vendor1 = new Vendor();
        vendor1.setFirstName("Abebe");
        Vendor vendor2 = new Vendor();
        vendor2.setFirstName("Shibiru");
        vendorService.saveVendor(vendor1);
        vendorService.saveVendor(vendor2);

        Category c1 = new Category();
        c1.setCategoryName("Phone");
        categoryService.save(c1);
        Category c2 = new Category();
        c2.setCategoryName("Watch");
        categoryService.save(c2);
        Category c3 = new Category();
        c3.setCategoryName("Book");
        categoryService.save(c3);

        Product p1 = new Product();
        p1.setProductName("Iphone10");
        p1.setCategory(c1);
        p1.setPrice(1000);
        p1.setQuantity(20);
        p1.setVendor(vendor1);
        productService.saveProduct(p1);

        Product p2 = new Product();
        p2.setProductName("Rolex");
        p2.setCategory(c2);
        p2.setPrice(3000);
        p2.setQuantity(50);
        p2.setVendor(vendor2);
        productService.saveProduct(p2);

        Product p3 = new Product();
        p3.setProductName("Esat Wey Abeba");
        p3.setCategory(c3);
        p3.setPrice(50);
        p3.setQuantity(30);
        p3.setVendor(vendor2);
        productService.saveProduct(p3);

        Product p4 = new Product();
        p3.setProductName("Seiko");
        p3.setCategory(c2);
        p3.setPrice(50);
        p3.setQuantity(30);
        p3.setVendor(vendor1);
        productService.saveProduct(p3);

        Product p5 = new Product();
        p3.setProductName("Galaxy10");
        p3.setCategory(c1);
        p3.setPrice(50);
        p3.setQuantity(30);
        p3.setVendor(vendor1);
        productService.saveProduct(p3);

        Product p6 = new Product();
        p3.setProductName("Tower in the Sky");
        p3.setCategory(c3);
        p3.setPrice(50);
        p3.setQuantity(30);
        p3.setVendor(vendor2);
        productService.saveProduct(p3);


    }
}
