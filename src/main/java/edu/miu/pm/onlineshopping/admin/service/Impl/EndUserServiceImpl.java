package edu.miu.pm.onlineshopping.admin.service.Impl;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.repository.EndUserRepository;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndUserServiceImpl implements EndUserService {
    @Autowired
    public EndUserRepository endUserRepository;

    @Override
    public EndUser createUser(EndUser user) {
        return endUserRepository.save(user);
    }
}
