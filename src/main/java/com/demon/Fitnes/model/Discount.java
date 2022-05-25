package com.demon.Fitnes.model;

import lombok.Data;

@Data
public class Discount {

    private Long id;
    private String clientLogin;
    private Service service;
    private Integer amount;
    private Integer numberVisits;
}
