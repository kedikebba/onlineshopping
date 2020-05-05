package edu.miu.pm.onlineshopping.product.controller;


import edu.miu.pm.onlineshopping.product.model.Category;
import edu.miu.pm.onlineshopping.product.model.ProductStatus;
import edu.miu.pm.onlineshopping.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;


    @GetMapping(value ="/categories")
    public List<Category> getAllCategories(){
        return (List<Category>) categoryService.findAll();
    }


    @GetMapping(value = "/category/{id}")
    public Category getCategoryById(@PathVariable("id") Long categoryId) {
        return categoryService.findById(categoryId);
    }


    @PostMapping(value = "/update_category", produces = "application/json")
    public Category updateCategory(@RequestBody Category category) {
        categoryService.save(category);
        return category;
    }


    @PostMapping(value = "/category", produces = "application/json")
    public Category save(@RequestBody Category category) {
        System.out.println(category);
        categoryService.save(category);
        return category;
    }


    @DeleteMapping(value = "/category/{id}")
    public Category delete(@PathVariable("id") Long categoryId) {
        Category category = categoryService.findById(categoryId);
        categoryService.delete(categoryId);
        return category;
    }

    @GetMapping("/categories/search")
    public List<Category> search(@RequestParam("categoryNumber") String categoryNumber, @RequestParam("categoryName") String categoryName,
                                 @RequestParam("status") ProductStatus status) {
        boolean byCategoryNumber = categoryNumber != null && !categoryNumber.isEmpty();
        boolean byCategoryName = categoryName != null && !categoryName.isEmpty();
        boolean byStatus = status == ProductStatus.ACTIVE;

        if (byCategoryName) {
            return categoryService.findByCategoryName(categoryName, status);
        } else if(byStatus) {
            return categoryService.findByStatus(status);
        }
        else
            return categoryService.findAll();

    }
}
