package edu.miu.pm.onlineshopping.security;

import edu.miu.pm.onlineshopping.admin.model.Account;
import edu.miu.pm.onlineshopping.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailService implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountService.findUserByEmail(s);
        return new AccountDetails(account);
    }
}
