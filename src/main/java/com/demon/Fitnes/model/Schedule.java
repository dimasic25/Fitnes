package com.demon.Fitnes.model;

import lombok.Data;

import java.sql.Time;

@Data
public class Schedule {

    private Long id;
    private Service service;
    private Coach coach;
    private Time startTime;
    private Time endTime;
    private String weekday;
    private Integer groupNumber;
}
