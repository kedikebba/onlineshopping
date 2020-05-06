package edu.miu.pm.onlineshopping.shoppingcart.service;


import edu.miu.pm.onlineshopping.admin.model.Address;
import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
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
    @Autowired
    private EndUserService endUserService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //loadData();
        System.out.println("Loading Initial data");

    }

    private void loadData(){
        EndUser user = new EndUser();
        user.setFirstName("John");
        Address address = new Address();
        address.setCity("Fairfield");
        address.setEmail("john@shopping.com");
        address.setPhoneNumber("6411112221212");
        address.setState("Iowa");
        address.setZipCode("52557");
        user.setAddress(address);
        endUserService.saveUser(user);

        Vendor vendor1 = new Vendor();
        vendor1.setFirstName("Abebe");
        Vendor vendor2 = new Vendor();
        vendor2.setFirstName("Shibiru");
        Vendor vendor3 = new Vendor();
        vendorService.saveVendor(vendor1);
        vendorService.saveVendor(vendor2);
        vendorService.saveVendor(vendor3);

        Category c1 = new Category();
        c1.setCategoryName("Phone");
        categoryService.save(c1);
        Category c2 = new Category();
        c2.setCategoryName("Watch");
        categoryService.save(c2);
        Category c3 = new Category();
        c3.setCategoryName("Book");
        categoryService.save(c3);
        Category c4 = new Category();
        c4.setCategoryName("Laptop");
        categoryService.save(c4);

        Product p1 = new Product();
        p1.setProductName("Iphone10");
        p1.setCategory(c1);
        p1.setPrice(1000);
        p1.setQuantity(20);
        p1.setStatus(1);
        p1.setApprovedStatus(true);
        p1.setVendor(vendor1);
        productService.saveProduct(p1);

        Product p2 = new Product();
        p2.setProductName("Rolex");
        p2.setCategory(c2);
        p2.setPrice(3000);
        p2.setQuantity(50);
        p2.setStatus(1);
        p2.setApprovedStatus(true);
        p2.setVendor(vendor2);
        productService.saveProduct(p2);

        Product p3 = new Product();
        p3.setProductName("Esat Wey Abeba");
        p3.setCategory(c3);
        p3.setPrice(50);
        p3.setQuantity(30);
        p3.setVendor(vendor2);
        p3.setStatus(1);
        p3.setApprovedStatus(true);
        productService.saveProduct(p3);

        Product p4 = new Product();
        p4.setProductName("Seiko");
        p4.setCategory(c2);
        p4.setPrice(50);
        p4.setQuantity(30);
        p4.setStatus(1);
        p4.setApprovedStatus(true);
        p4.setVendor(vendor1);
        productService.saveProduct(p4);

        Product p5 = new Product();
        p5.setProductName("Galaxy10");
        p5.setCategory(c1);
        p5.setPrice(50);
        p5.setQuantity(30);
        p5.setStatus(1);
        p5.setApprovedStatus(true);
        p5.setVendor(vendor1);
        productService.saveProduct(p5);

        Product p6 = new Product();
        p6.setProductName("Tower in the Sky");
        p6.setCategory(c3);
        p6.setPrice(50);
        p6.setQuantity(30);
        p6.setStatus(1);
        p6.setApprovedStatus(true);
        p6.setVendor(vendor2);
        productService.saveProduct(p6);

        Product p7 = new Product();
        p7.setProductName("McBook Pro");
        p7.setCategory(c4);
        p7.setPrice(50);
        p7.setQuantity(30);
        p7.setStatus(1);
        p7.setApprovedStatus(true);
        p7.setVendor(vendor3);
        productService.saveProduct(p7);

        Product p8 = new Product();
        p8.setProductName("Microsoft Surface");
        p8.setCategory(c4);
        p8.setPrice(50);
        p8.setQuantity(30);
        p8.setStatus(1);
        p8.setApprovedStatus(true);
        p8.setVendor(vendor3);
        productService.saveProduct(p8);

        Product p9 = new Product();
        p9.setProductName("Dell XPS");
        p9.setCategory(c4);
        p9.setPrice(50);
        p9.setQuantity(30);
        p9.setStatus(1);
        p9.setApprovedStatus(true);
        p9.setVendor(vendor3);
        productService.saveProduct(p9);


    }
}
