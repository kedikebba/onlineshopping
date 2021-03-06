package edu.miu.pm.onlineshopping.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.miu.pm.onlineshopping.admin.model.Status;
import edu.miu.pm.onlineshopping.admin.model.Vendor;

////////////////     Contributor:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {
	
	@Query("FROM Vendor WHERE status = :status")
	public List<Vendor> findAllByStatus(@Param("status") Status status);
	
	public Vendor findByVendorIdAndStatus(int vendorId,Status status);
	

    Vendor findByFirstName(String name);

    Vendor findByAccount_Email(String email);

}
