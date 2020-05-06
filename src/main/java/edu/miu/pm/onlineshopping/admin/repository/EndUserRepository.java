package edu.miu.pm.onlineshopping.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.model.Status;

////////////////     Contributor:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Repository
public interface EndUserRepository extends JpaRepository<EndUser,Integer>{
	
	@Query("FROM EndUser WHERE status = :status")
	public List<EndUser> findAllByStatus(@Param("status") Status status);
	
	public EndUser findByUserIdAndStatus(int userId,Status status);
	

}
