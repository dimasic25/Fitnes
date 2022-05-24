package com.demon.Fitnes.model.builder;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.model.Subscription;

public final class ServiceSubscriptionBuilder {
    private Subscription subscription;
    private Service service;
    private Integer numberSessions;
    private Integer numberVisits;
    private Integer groupNumber;

    private ServiceSubscriptionBuilder() {
    }

    public static ServiceSubscriptionBuilder aServiceSubscription() {
        return new ServiceSubscriptionBuilder();
    }

    public ServiceSubscriptionBuilder subscription(Subscription subscription) {
        this.subscription = subscription;
        return this;
    }

    public ServiceSubscriptionBuilder service(Service service) {
        this.service = service;
        return this;
    }

    public ServiceSubscriptionBuilder numberSessions(Integer numberSessions) {
        this.numberSessions = numberSessions;
        return this;
    }

    public ServiceSubscriptionBuilder numberVisits(Integer numberVisits) {
        this.numberVisits = numberVisits;
        return this;
    }

    public ServiceSubscriptionBuilder groupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public ServiceSubscription build() {
        ServiceSubscription serviceSubscription = new ServiceSubscription();
        serviceSubscription.setSubscription(subscription);
        serviceSubscription.setService(service);
        serviceSubscription.setNumberSessions(numberSessions);
        serviceSubscription.setNumberVisits(numberVisits);
        serviceSubscription.setGroupNumber(groupNumber);
        return serviceSubscription;
    }
}
