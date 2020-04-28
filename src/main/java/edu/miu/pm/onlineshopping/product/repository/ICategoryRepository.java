package edu.miu.pm.onlineshopping.product.repository;

import edu.miu.pm.onlineshopping.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT p FROM Category p WHERE lower(p.categoryName) like %?1% " +
            "and p.status = ?2 order by p.categoryName")
    public List<Category> findByCategoryName(String categoryName, Integer status);

    @Query("SELECT p FROM Category p WHERE  " +
            "p.status = ?1 order by p.categoryName")
    public List<Category> findByStatus(Integer status);
}
