package com.example.Fitnes.service.impl;

import com.example.Fitnes.model.Subscription;
import com.example.Fitnes.repository.ClientRepository;
import com.example.Fitnes.repository.SubscriptionRepository;
import com.example.Fitnes.service.SubscriptionService;
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
