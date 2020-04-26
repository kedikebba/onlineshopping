package edu.miu.pm.onlineshopping.product.service;

import edu.miu.pm.onlineshopping.product.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> searchProduct(String search);
}
