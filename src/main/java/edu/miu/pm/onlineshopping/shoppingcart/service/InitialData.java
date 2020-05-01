package edu.miu.pm.onlineshopping.shoppingcart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class InitialData implements ApplicationRunner {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private ProductRespository productRespository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        loadData();
        System.out.println("Initial data");

    }

    private void loadData(){
//        User vendor1 = new User();
//        vendor1.setFirstName("Abebe");
//        User vendor2 = new User();
//        vendor2.setFirstName("Shibiru");
//        userRepository.save(vendor1);
//        userRepository.save(vendor2);
//
//        Category c1 = new Category();
//        c1.setCategoryName("Phone");
//        Category c2 = new Category();
//        c2.setCategoryName("Watch");
//        Category c3 = new Category();
//        c3.setCategoryName("Book");
//
//        Product p1 = new Product();
//        p1.setProductName("Iphone10");
//        p1.setCategory(c1);
//        c1.setProductList(p1);
//        p1.setCreatedDate(LocalDate.now());
//        p1.setPrice(1000);
//        p1.setQuantity(20);
//        p1.setVendor(vendor1);
//        categoryRepository.save(c1);
//        productRespository.save(p1);
//
//        Product p2 = new Product();
//        p2.setProductName("Rolex");
//        p2.setCategory(c2);
//        c2.setProductList(p2);
//        p2.setCreatedDate(LocalDate.now());
//        p2.setPrice(3000);
//        p2.setQuantity(50);
//        p2.setVendor(vendor2);
//        categoryRepository.save(c2);
//        productRespository.save(p2);
//
//        Product p3 = new Product();
//        p3.setProductName("Esat Wey Abeba");
//        p3.setCategory(c3);
//        c3.setProductList(p3);
//        p3.setCreatedDate(LocalDate.now());
//        p3.setPrice(50);
//        p3.setQuantity(30);
//        p3.setVendor(vendor2);
//        categoryRepository.save(c3);
//        productRespository.save(p3);


    }
}
