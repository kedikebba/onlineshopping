package edu.miu.pm.onlineshopping.admin.repository;

import edu.miu.pm.onlineshopping.admin.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
