package edu.miu.pm.onlineshopping.shoppingcart.repository;

import edu.miu.pm.onlineshopping.shoppingcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
