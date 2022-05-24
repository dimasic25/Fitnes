package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.repository.ServiceRepository;
import com.demon.Fitnes.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServServiceImpl implements ServService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service getService(Long id) {
        return serviceRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Service> getServices() {
        return serviceRepository.findAllServices();
    }

    @Override
    public Long addService(Service service) {
        return null;
    }

    @Override
    public void updateService(Service service) {

    }
}
