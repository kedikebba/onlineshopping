package edu.miu.pm.onlineshopping.product.service.Imp;

import edu.miu.pm.onlineshopping.product.model.Category;
import edu.miu.pm.onlineshopping.product.repository.ICategoryRepository;
import edu.miu.pm.onlineshopping.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
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
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long categoryId) {
         categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> findByCategoryName(String categoryName, Integer status) {
        return categoryRepository.findByCategoryName(categoryName,status);
    }

    @Override
    public List<Category> findByStatus(Integer status) {
        return categoryRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
}
