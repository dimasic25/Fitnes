package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.repository.ScheduleRepository;
import com.demon.Fitnes.repository.ServiceRepository;
import com.demon.Fitnes.repository.ServiceSubscriptionRepository;
import com.demon.Fitnes.service.ServiceSubsriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceSubscriptionServiceImpl implements ServiceSubsriptionService {

    private final ServiceSubscriptionRepository serviceSubscriptionRepository;
    private final ServiceRepository serviceRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ServiceSubscriptionServiceImpl(ServiceSubscriptionRepository serviceSubscriptionRepository, ServiceRepository serviceRepository, ScheduleRepository scheduleRepository) {
        this.serviceSubscriptionRepository = serviceSubscriptionRepository;
        this.serviceRepository = serviceRepository;
        this.scheduleRepository = scheduleRepository;
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

    @Override
    public ServiceSubscription getServiceSub(Long subId, Long serviceId) {
        ServiceSubscription serviceSubscription = serviceSubscriptionRepository.findByServiceAndSubscription(serviceId, subId).orElse(null);

        return serviceSubscription;
    }

    @Override
    public void save(ServiceSubscription serviceSubscription) throws Exception {
        List<Integer> groupNumbers = scheduleRepository.findGroupNumbersByService(serviceSubscription.getService().getId());
        if (groupNumbers.contains(serviceSubscription.getGroupNumber())) {
            serviceSubscriptionRepository.insert(serviceSubscription);
        } else {
            throw new Exception("Группа в данный момент недоступна! Выберите другую!");
        }
    }

    @Override
    public void update(ServiceSubscription serviceSubscription) throws Exception {
        List<Integer> groupNumbers = scheduleRepository.findGroupNumbersByService(serviceSubscription.getService().getId());
        if (groupNumbers.contains(serviceSubscription.getGroupNumber())) {
            serviceSubscriptionRepository.delete(serviceSubscription.getService().getId(), serviceSubscription.getSubscription().getId());
            serviceSubscriptionRepository.insert(serviceSubscription);
        } else {
            throw new Exception("Группа в данный момент недоступна! Выберите другую!");
        }
    }

    @Override
    public void delete(Long serviceId, Long subscriptionId) {
        serviceSubscriptionRepository.delete(serviceId, subscriptionId);
    }
}
