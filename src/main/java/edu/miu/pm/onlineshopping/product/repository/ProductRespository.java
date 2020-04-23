package edu.miu.pm.onlineshopping.product.repository;

import edu.miu.pm.onlineshopping.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {
}
