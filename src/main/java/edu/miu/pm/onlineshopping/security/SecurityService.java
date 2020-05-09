package edu.miu.pm.onlineshopping.security;

public interface SecurityService {

    public AccountDetails findLoggedInUser();

    void autoLogin(String username, String password);
}
