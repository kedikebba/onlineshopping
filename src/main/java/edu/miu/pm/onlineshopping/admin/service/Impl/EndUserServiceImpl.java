package edu.miu.pm.onlineshopping.admin.service.Impl;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.repository.EndUserRepository;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

////////////////     Contributor:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Service
@EnableTransactionManagement
public class EndUserServiceImpl implements EndUserService {
    @Autowired
    public EndUserRepository endUserRepository;

    @Override
    @Transactional
    public EndUser saveUser(EndUser user) {
        return endUserRepository.save(user);
    }

    @Override
    public EndUser getEndUserbyId(int id) {
        return endUserRepository.findById(id).get();
    }

    @Override
    public List<EndUser> getAllEndUsers() {
        return endUserRepository.findAll();
    }
}
