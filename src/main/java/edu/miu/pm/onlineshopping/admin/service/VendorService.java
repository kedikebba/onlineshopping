package edu.miu.pm.onlineshopping.admin.service;

import edu.miu.pm.onlineshopping.admin.model.Vendor;

public interface VendorService {

    public Vendor saveVendor(Vendor vendor);
    public Vendor getVendorByName(String name);

}
