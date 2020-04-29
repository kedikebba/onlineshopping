package edu.miu.pm.onlineshopping.shoppingcart.service.Impl;

import edu.miu.pm.onlineshopping.shoppingcart.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.repository.ProductRespository;
import edu.miu.pm.onlineshopping.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRespository productRespository;

    @Override
    public List<Product> getAllProducts() {
        return productRespository.findAll();
    }

    @Override
    public List<Product> searchProduct(String search) {
//        return productRespository.findAllByProductNameOrCategory_CategoryNameOrVendor_FirstName(search, search, search);
          return productRespository.findAllByProductNameContainsOrCategory_CategoryNameContainsOrVendor_FirstNameContains(search, search, search);
    }

    @Override
    public List<Product> getProducts(Set<Long> keySet) {
        List<Product> products = new ArrayList<>();
        for(Long id: keySet){
            products.add(productRespository.findById(id).get());
        }

        return products;
    }
}
