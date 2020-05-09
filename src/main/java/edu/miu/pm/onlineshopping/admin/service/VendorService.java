package edu.miu.pm.onlineshopping.admin.service;

import edu.miu.pm.onlineshopping.admin.model.Vendor;

import java.util.List;

public interface VendorService {

    public Vendor saveVendor(Vendor vendor);
    public Vendor getVendorByName(String name);

    List<Vendor> getAllVendors();

    Vendor getVendorById(int id);

    Vendor getVendorByEmail(String email);
}
