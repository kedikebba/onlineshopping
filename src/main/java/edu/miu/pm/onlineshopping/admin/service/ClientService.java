package edu.miu.pm.onlineshopping.admin.service;

import edu.miu.pm.onlineshopping.admin.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();

    Client saveClient(Client admin);
}
