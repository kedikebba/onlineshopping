package edu.miu.pm.onlineshopping.admin.service.Impl;

import edu.miu.pm.onlineshopping.admin.model.Address;
import edu.miu.pm.onlineshopping.admin.repository.AddressRepository;
import edu.miu.pm.onlineshopping.admin.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableTransactionManagement
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddress(String street, String state, String city, String zipCode) {
        return addressRepository.findByStreetAndStateAndCityAndZipCode(street, state, city, zipCode);
    }
}
