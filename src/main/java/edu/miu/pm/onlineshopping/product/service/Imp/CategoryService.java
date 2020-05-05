package edu.miu.pm.onlineshopping.product.service.Imp;

import edu.miu.pm.onlineshopping.product.model.Category;
import edu.miu.pm.onlineshopping.product.model.ProductStatus;
import edu.miu.pm.onlineshopping.product.repository.ICategoryRepository;
import edu.miu.pm.onlineshopping.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {


    private ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll(Sort.by("categoryName"));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public void delete(Long categoryId) {
         categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findByCategoryName(String categoryName, ProductStatus status) {
        return categoryRepository.findByCategoryName(categoryName,status);
    }

    @Override
    public List<Category> findByStatus(ProductStatus status) {
        return categoryRepository.findByStatus(status);
    }
}
