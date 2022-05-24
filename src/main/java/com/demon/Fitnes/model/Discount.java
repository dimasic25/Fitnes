package com.demon.Fitnes.model;

import lombok.Data;

@Data
public class Discount {

    private Long id;
    private Service service;
    private Integer amount;
    private Integer numberVisits;
}
