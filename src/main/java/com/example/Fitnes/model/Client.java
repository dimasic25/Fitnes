package com.example.Fitnes.model;

import lombok.Data;

import java.util.Date;

@Data
public class Client {

    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthdate;
    private Integer numberSubs;
    private Double price;
}
