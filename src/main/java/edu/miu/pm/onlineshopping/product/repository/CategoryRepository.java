package edu.miu.pm.onlineshopping.product.repository;

import edu.miu.pm.onlineshopping.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
