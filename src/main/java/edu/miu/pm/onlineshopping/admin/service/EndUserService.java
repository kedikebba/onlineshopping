package edu.miu.pm.onlineshopping.admin.service;

import edu.miu.pm.onlineshopping.admin.model.EndUser;

import java.util.List;

public interface EndUserService {

    public EndUser saveUser(EndUser user);
    public EndUser getEndUserbyId(int id);

    List<EndUser> getAllEndUsers();
}
