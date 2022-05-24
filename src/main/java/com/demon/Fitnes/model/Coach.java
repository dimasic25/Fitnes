package com.demon.Fitnes.model;

import lombok.Data;

import java.util.Date;

@Data
public class Coach {

    private String login;
    private String firstName;
    private String secondName;
    private String middleName;
    private Date birthdate;
    private String phone;
    private Long experience;
}
