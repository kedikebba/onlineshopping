package edu.miu.pm.onlineshopping.shoppingcart.service;

import edu.miu.pm.onlineshopping.shoppingcart.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> searchProduct(String search);

    List<Product> getProducts(Set<Long> keySet);
}
