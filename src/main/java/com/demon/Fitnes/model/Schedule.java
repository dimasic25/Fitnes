package com.demon.Fitnes.model;

import lombok.Data;

import java.time.LocalTime;


@Data
public class Schedule {

    private Long id;
    private Service service;
    private Coach coach;
    private LocalTime startTime;
    private LocalTime endTime;
    private String weekday;
    private Integer groupNumber;
}
