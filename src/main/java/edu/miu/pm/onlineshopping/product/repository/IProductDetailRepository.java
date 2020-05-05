package edu.miu.pm.onlineshopping.product.repository;

import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface IProductDetailRepository extends JpaRepository<ProductDetail,Long> {
    @Query("SELECT sum(d.quantity) FROM ProductDetail d where d.product.productId = ?1 group by d.product")
    public int getProductStockQuantity(Long productId);
    ProductDetail findByProduct(Product product);

}
