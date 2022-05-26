package com.demon.Fitnes.service;

import com.demon.Fitnes.model.ServiceSubscription;

import java.text.ParseException;
import java.util.List;

public interface ServiceSubsriptionService {

    List<ServiceSubscription> getServiceSubsBySub(Long subId);

    ServiceSubscription getServiceSub(Long subId, Long serviceId) throws Exception;

    void save(ServiceSubscription serviceSubscription) throws Exception;

    void update(ServiceSubscription serviceSubscription) throws Exception;

    void delete(Long serviceId, Long subscriptionId);
}
