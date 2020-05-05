package edu.miu.pm.onlineshopping.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.pm.onlineshopping.admin.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

    Vendor findByFirstName(String name);

}
