package com.demon.Fitnes.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Client {

    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String phone;
}
