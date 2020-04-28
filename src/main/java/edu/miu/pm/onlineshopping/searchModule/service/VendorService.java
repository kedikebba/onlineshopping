package edu.miu.pm.onlineshopping.searchModule.service;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.searchModule.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    @Autowired
     public VendorRepository vendorRepository;

    public Vendor search(String key){
        Object keyObject = key;
        //System.out.println(obj instanceof Integer);
         if(keyObject instanceof Integer){
             return vendorRepository.findById(Integer.parseInt(key)).orElse(null);
         }
         else {
              Vendor vendor=vendorRepository.searchByName(key);
              if(vendor==null){
                  return null;
              }

              return vendor;
         }

    }
}
