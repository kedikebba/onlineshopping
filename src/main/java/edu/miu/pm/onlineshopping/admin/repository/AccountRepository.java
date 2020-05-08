package edu.miu.pm.onlineshopping.admin.repository;

import edu.miu.pm.onlineshopping.admin.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
