package com.demon.Fitnes.service;

import com.demon.Fitnes.model.ServiceSubscription;

import java.util.List;

public interface ServiceSubsriptionService {

    List<ServiceSubscription> getServiceSubsBySub(Long subId);
}
