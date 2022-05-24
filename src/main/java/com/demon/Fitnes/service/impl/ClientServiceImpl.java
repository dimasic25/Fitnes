package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.repository.ClientRepository;
import com.demon.Fitnes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client verifyClient(Client client) throws Exception {
        if (clientRepository.findByLoginAndPassword(client).isPresent()) {
            return client;
        } else {
            throw new Exception("Клиента с таким логином не существует!");
        }
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    @Override
    public Client getClientByLogin(String login) {
        return clientRepository.findByLogin(login).get();
    }

    @Override
    public String registerClient(Client client) throws Exception {
        return clientRepository.insert(client);
    }
}
