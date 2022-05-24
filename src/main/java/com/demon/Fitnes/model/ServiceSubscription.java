package com.demon.Fitnes.model;

import lombok.Data;

@Data
public class ServiceSubscription {

    private Subscription subscription;

    private Service service;

    private Integer numberSessions;

    private Integer numberVisits;

    private Integer groupNumber;
}
