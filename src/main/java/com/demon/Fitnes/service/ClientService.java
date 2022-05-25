package com.demon.Fitnes.service;

import com.demon.Fitnes.model.Client;

import java.util.List;

public interface ClientService {

    Client verifyClient(Client client) throws Exception;
    List<Client> getAllClients();

    Client getClientByLogin(String login);

    String registerClient(Client client) throws Exception;

    void deleteUser(String login);
}
