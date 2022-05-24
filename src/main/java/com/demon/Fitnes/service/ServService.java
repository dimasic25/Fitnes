package com.demon.Fitnes.service;

import com.demon.Fitnes.model.Service;

import java.util.List;

public interface ServService {

    Service getService(Long id);

    List<Service> getServices();
    Long addService(Service service);
    void updateService(Service service);
}

