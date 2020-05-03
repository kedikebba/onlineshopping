package edu.miu.pm.onlineshopping.product.repository;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> getAllByStatus(String status);

    List<Product> findAllByVendor(Vendor vendor);

    @Query("SELECT p FROM Product p WHERE lower(p.productNumber) like %?1% " +
           "and LOWER(p.productName) like %?2% " +
            "and p.price >= ?3 and p.price < ?4 " +
            "and p.status = ?5 order by p.productName")
    List<Product> findAll(String productNumber, String productName, Double minProductPrice, Double maxProductPrice, Integer status);

    @Query("SELECT p FROM Product p WHERE lower(p.productNumber) like %?1% " +
            "and LOWER(p.productName) like %?2% " +
            "and p.status = ?3 order by p.productName")
    List<Product> findByNameNumber(String productNumber, String productName, Integer status);

    @Query("SELECT p FROM Product p WHERE  " +
            "LOWER(p.productName) like %?1% " +
            "and p.price >= ?2 and p.price < ?3 " +
            "and p.status = ?4 order by p.productName")
    List<Product> findByNamePrice(String productName, Double minProductPrice,
                                  Double maxProductPrice, Integer status);

    @Query("SELECT p FROM Product p WHERE lower(p.productNumber) like %?1% " + "and p.price >= ?2 and p.price < ?3 " +
            "and p.status = ?4 order by p.productName")
    List<Product> findByNumberPrice(String productNumber, Double minProductPrice,
                                    Double maxProductPrice, Integer status);

    @Query("SELECT p FROM Product p WHERE  " +
            "LOWER(p.productName) like %?1 " +
            "and p.status = ?2 order by p.productName")
    List<Product> findByName(String productName, Integer status);

    @Query("SELECT p FROM Product p WHERE lower(p.productNumber) like %?1% " +
            "and p.status = ?2 order by p.productName")
    List<Product> findByNumber(String productNumber, Integer status);

    @Query("SELECT p FROM Product p WHERE  " +
            "p.status = ?1 order by p.productName")
    List<Product> findByStatus(Integer status);

    @Query("SELECT p FROM Product p WHERE " +
            " p.price >= ?1 and p.price < ?2 " +
            "and p.status = ?3 order by p.productName")
    List<Product> findByPrice(Double minProductPrice,Double maxProductPrice, Integer status);

    @Query("SELECT p FROM Product p join p.category c WHERE lower(c.categoryName) like %?1% order by p.productName")
    List<Product> findByProductNameContainsOrCategory(String categoryName);
    @Query("SELECT p FROM Product p join p.vendor v WHERE lower(v.firstName) like %?1%  order by p.productName")
   List<Product> findByCategoryNameContainsOrVendor(String categoryNumber);

    @Query("SELECT p FROM Product p join p.vendor v  WHERE lower(v.firstName) like %?1% order by p.productId")
    List<Product> findByFirstNameContain(String firstName);
}


