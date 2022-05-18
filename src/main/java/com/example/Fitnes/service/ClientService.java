package com.example.Fitnes.service;

import com.example.Fitnes.model.Client;

import java.util.List;

public interface ClientService {

    Client verifyClient(Client client) throws Exception;
    List<Client> getAllClients();
}
