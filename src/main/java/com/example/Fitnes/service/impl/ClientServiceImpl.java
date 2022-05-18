package com.example.Fitnes.service.impl;

import com.example.Fitnes.model.Client;
import com.example.Fitnes.repository.ClientRepository;
import com.example.Fitnes.service.ClientService;
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
}
