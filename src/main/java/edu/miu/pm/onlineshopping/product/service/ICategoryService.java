package edu.miu.pm.onlineshopping.product.service;


import edu.miu.pm.onlineshopping.product.model.Category;
import edu.miu.pm.onlineshopping.product.model.ProductStatus;

import java.util.List;
public interface ICategoryService {

    public List<Category> findAll();

    public Category save(Category category);

    public Category findById(Long categoryId);

    public void delete(Long categoryId);
    public  Category updateCategory(Category category);

    public List<Category> findByCategoryName(String categoryName, ProductStatus status);

    public List<Category> findByStatus(ProductStatus status);
}
