package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.repository.ClientRepository;
import com.demon.Fitnes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Client verifyClient(Client client) throws Exception {
        boolean isUserPresent = clientRepository.findByLogin(client.getLogin()).isPresent();

        if (isUserPresent) {
            Client dbClient = clientRepository.findByLogin(client.getLogin()).get();
            if (passwordEncoder.matches(client.getPassword(), dbClient.getPassword())) {
                return client;
            } else {
                throw new Exception("Введен неверный пароль!");
            }
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
        String hashPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(hashPassword);
        return clientRepository.insert(client);
    }

    @Override
    public void deleteUser(String login) {
        clientRepository.deleteClient(login);
    }
}
