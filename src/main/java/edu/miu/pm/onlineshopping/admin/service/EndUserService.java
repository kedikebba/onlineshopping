package edu.miu.pm.onlineshopping.admin.service;

import edu.miu.pm.onlineshopping.admin.model.EndUser;

public interface EndUserService {

    public EndUser saveUser(EndUser user);
    public EndUser getEndUserbyId(int id);

}
