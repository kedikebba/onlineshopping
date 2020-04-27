package edu.miu.pm.onlineshopping.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.pm.onlineshopping.admin.model.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {

}
