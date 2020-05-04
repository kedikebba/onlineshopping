package edu.miu.pm.onlineshopping.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {
	
	@Query("FROM Vendor WHERE status = 'INACTIVE'")
	public List<Vendor> getInactiveEndUsers();
	@Query("FROM EndUser WHERE status = 'INACTIVE' AND vendorId = ?1")
	public Vendor getInactiveVendor(int vendorId);

}
