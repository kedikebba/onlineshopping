package edu.miu.pm.onlineshopping.product.service;

import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return productRespository.findAllByProductNameOrCategory_CategoryNameOrVendor_FirstName(search, search, search);
    }
}
