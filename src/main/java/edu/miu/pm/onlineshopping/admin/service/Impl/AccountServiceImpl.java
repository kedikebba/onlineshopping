package edu.miu.pm.onlineshopping.admin.service.Impl;

import edu.miu.pm.onlineshopping.admin.model.Account;
import edu.miu.pm.onlineshopping.admin.repository.AccountRepository;
import edu.miu.pm.onlineshopping.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findUserByEmail(String email) {
        List<Account> users= (List<Account>) accountRepository.findAll();
        for (Account u :
                users) {
            System.out.println(u.getEmail().equalsIgnoreCase(email));
            if(u.getEmail().equalsIgnoreCase(email)){
                System.out.println(u.getEmail());
                return u;
            }

        }
        return null;
    }

    @Override
    public Account save(Account user) {
        Account updatedUser = accountRepository.save(user);
        return updatedUser;

    }

    @Override
    public Account findById(int id) {
        return accountRepository.findById(id).orElse(null);
    }
}
