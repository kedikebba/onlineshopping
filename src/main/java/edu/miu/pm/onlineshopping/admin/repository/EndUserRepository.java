package edu.miu.pm.onlineshopping.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import org.springframework.stereotype.Repository;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser,Integer>{

}
