package edu.miu.pm.onlineshopping.admin.controller.repository;

import edu.miu.pm.onlineshopping.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
