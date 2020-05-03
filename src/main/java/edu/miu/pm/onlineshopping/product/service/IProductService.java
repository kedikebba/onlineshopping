package edu.miu.pm.onlineshopping.product.service;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public List<Product> searchProduct(String seach);
    public Product findById(Long productId);

    public void delete(Long productId);

    public List<Product> findByAll(String productNumber, String productName, Double minProductPrice, Double maxProductPrice, Integer status);

    public List<Product> findByNameNumber(String productNumber, String productName, Integer status);

    public List<Product> findByNamePrice(String productName, Double minProductPrice, Double maxProductPrice, Integer status);

    public List<Product> findByNumberPrice(String productNumber, Double minProductPrice, Double maxProductPrice, Integer status);

    public List<Product> findByName(String productName, Integer status);

    public List<Product> findByNumber(String productNumber, Integer status);

    public List<Product> findByStatus(Integer status);

    public List<Product> findByPrice(Double minProductPrice, Double maxProductPrice, Integer status);

    public Product saveProduct(Product product);
    List<Product> findByProductNameContainsOrCategory(String categoryName);
    List<Product> findByCategoryNameContainsOrVendor(String categoryNumber);
    List<Product> findByFirstNameContain(String firstName);


    List<Product> findAllByProductNameContainsOrCategory_CategoryNameContainsOrVendor_FirstNameContains(String ProductNameContainsOrCategory, String CategoryNameContainsOrVendor, String FirstNameContains);


}
