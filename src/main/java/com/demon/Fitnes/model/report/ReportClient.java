package com.demon.Fitnes.model.report;

import lombok.Data;

@Data
public class ReportClient {

    private String login;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private Integer countSubscriptions;
}
