package com.example.Fitnes.model;

import lombok.Data;

@Data
public class Subscription {

    private Long id;
    private String clientLogin;
    private String startDate;
    private String endDate;
    private boolean closed;
    private double price;
}
