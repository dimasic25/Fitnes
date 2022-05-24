package com.demon.Fitnes.service;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getAllSubs();

    List<Subscription> getSubsByClient(String clientLogin);
    Subscription getById(Long id);
    void save(Subscription subscription) throws Exception;
    void update(Subscription subscription) throws Exception;
    void delete(Long id);
}
