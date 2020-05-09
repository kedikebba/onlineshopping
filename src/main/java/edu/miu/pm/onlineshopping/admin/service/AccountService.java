package edu.miu.pm.onlineshopping.admin.service;

import edu.miu.pm.onlineshopping.admin.model.Account;

public interface AccountService {

    public Account findUserByEmail(String email);
    Account save(Account user);
    Account findById(int id);
}
