package edu.miu.pm.onlineshopping.admin.repository;

import edu.miu.pm.onlineshopping.admin.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

////////////////     Contributor:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findByStreetAndStateAndCityAndZipCode(String street, String state, String city, String zipCode);
}
