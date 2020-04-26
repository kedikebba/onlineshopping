package edu.miu.pm.onlineshopping.product.repository;

import edu.miu.pm.onlineshopping.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {

//    List<Product> findALLByProductNameOrCategoryOrderByCategory(String search1, String search2);
    List<Product> findAllByProductNameOrCategory_CategoryNameOrVendor_FirstName(String search1, String search2, String search3);
}
