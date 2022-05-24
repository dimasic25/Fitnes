package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.Subscription;
import com.demon.Fitnes.repository.ClientRepository;
import com.demon.Fitnes.repository.SubscriptionRepository;
import com.demon.Fitnes.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, ClientRepository clientRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Subscription> getAllSubs() {
        return subscriptionRepository.findAllSubs();
    }

    @Override
    public List<Subscription> getSubsByClient(String clientLogin) {
        return subscriptionRepository.findSubsByClient(clientLogin);
    }

    @Override
    public Subscription getById(Long id) {
        return subscriptionRepository.findById(id).get();
    }

    @Override
    public void save(Subscription subscription) throws Exception {
        if (clientRepository.findByLogin(subscription.getClientLogin()).isPresent()) {
            subscriptionRepository.insert(subscription);
        } else {
            throw new Exception("Клиента с таким логином не существует!");
        }
    }

    @Override
    public void update(Subscription subscription) throws Exception {
        if (clientRepository.findByLogin(subscription.getClientLogin()).isPresent()) {
            subscriptionRepository.update(subscription);
        } else {
            throw new Exception("Клиента с таким логином не существует!");
        }
    }

    @Override
    public void delete(Long id) {
        subscriptionRepository.delete(id);
    }
}
