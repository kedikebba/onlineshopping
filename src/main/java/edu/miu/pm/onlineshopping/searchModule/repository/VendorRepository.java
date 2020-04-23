package edu.miu.pm.onlineshopping.searchModule.repository;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, Integer> {
    @Query("select v from Vendor v where v.name=?1")
     public Vendor searchByName( String name);

    @Query("select v from Vendor v where v.name=?1 or v.address.state =?1 or ")
    public Vendor searchByKey( String key);

}
