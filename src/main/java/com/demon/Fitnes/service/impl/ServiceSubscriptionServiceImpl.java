package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.repository.ServiceRepository;
import com.demon.Fitnes.repository.ServiceSubscriptionRepository;
import com.demon.Fitnes.service.ServiceSubsriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceSubscriptionServiceImpl implements ServiceSubsriptionService {

    private final ServiceSubscriptionRepository serviceSubscriptionRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceSubscriptionServiceImpl(ServiceSubscriptionRepository serviceSubscriptionRepository, ServiceRepository serviceRepository) {
        this.serviceSubscriptionRepository = serviceSubscriptionRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceSubscription> getServiceSubsBySub(Long subId) {
        List<ServiceSubscription> serviceSubscriptions = serviceSubscriptionRepository.findServiceSubsBySub(subId);
        for (ServiceSubscription serviceSub:
               serviceSubscriptions) {
            Service service = serviceRepository.findById(serviceSub.getService().getId()).get();
            serviceSub.setService(service);
        }

        return serviceSubscriptions;
    }
}
