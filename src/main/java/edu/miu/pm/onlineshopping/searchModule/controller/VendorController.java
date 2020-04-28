package edu.miu.pm.onlineshopping.searchModule.controller;
import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.searchModule.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/vendor")
public class VendorController {
    @Autowired
   public VendorService vendorService;

    @GetMapping(value="/{searchKey}")
    public Vendor getVendor(@PathVariable String searchKey ){

        return vendorService.search(searchKey);
    }
}
