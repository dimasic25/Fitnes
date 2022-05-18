package com.example.Fitnes.service;

import com.example.Fitnes.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getAllSubs();
    Subscription getById(Long id);
    void save(Subscription subscription) throws Exception;
    void update(Subscription subscription) throws Exception;
    void delete(Long id);
}
