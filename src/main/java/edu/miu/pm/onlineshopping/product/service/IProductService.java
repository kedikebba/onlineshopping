package edu.miu.pm.onlineshopping.product.service;

import edu.miu.pm.onlineshopping.product.model.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();

    //public Product save(Product product, User user);

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

    //added by Getaneh
    public List<Product> searchProduct(String search);
    public Product saveProduct(Product product);

    Product getByProductNameAndCategory(String productName, String categoryName);

    void deleteProductById(long id);
}
