package edu.miu.pm.onlineshopping.admin.service.Impl;

import edu.miu.pm.onlineshopping.admin.model.Client;
import edu.miu.pm.onlineshopping.admin.repository.ClientRepository;
import edu.miu.pm.onlineshopping.admin.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(Client admin) {
        return clientRepository.save(admin);
    }

    @Override
    public Client getAdminByEmail(String email) {
        return clientRepository.findByAccount_Email(email);
    }
}
