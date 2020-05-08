package edu.miu.pm.onlineshopping.admin.service;

import edu.miu.pm.onlineshopping.admin.model.Address;

public interface AddressService {

    Address saveAddress(Address address);

    Address getAddress(String street, String state, String city, String zipCode);
}
