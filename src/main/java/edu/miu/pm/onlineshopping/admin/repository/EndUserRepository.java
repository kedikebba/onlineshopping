package edu.miu.pm.onlineshopping.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
@Repository
public interface EndUserRepository extends JpaRepository<EndUser,Integer>{
	
	@Query("FROM EndUser WHERE status = 'INACTIVE'")
	public List<EndUser> getInactiveEndUsers();
	
	@Query("FROM EndUser WHERE status = 'INACTIVE' AND endUserId = ?1")
	public EndUser getInactiveEndUser(int endUserId);

}
