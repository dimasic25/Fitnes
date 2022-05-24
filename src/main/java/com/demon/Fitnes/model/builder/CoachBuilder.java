package com.demon.Fitnes.model.builder;

import com.demon.Fitnes.model.Coach;

import java.util.Date;

public final class CoachBuilder {
    private String login;
    private String firstName;
    private String secondName;
    private String middleName;
    private Date birthdate;
    private String phone;
    private Long experience;

    private CoachBuilder() {
    }

    public static CoachBuilder newBuilder() {
        return new CoachBuilder();
    }

    public CoachBuilder login(String login) {
        this.login = login;
        return this;
    }

    public CoachBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CoachBuilder secondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public CoachBuilder middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public CoachBuilder birthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public CoachBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public CoachBuilder experience(Long experience) {
        this.experience = experience;
        return this;
    }

    public Coach build() {
        Coach coach = new Coach();
        coach.setLogin(login);
        coach.setFirstName(firstName);
        coach.setSecondName(secondName);
        coach.setMiddleName(middleName);
        coach.setBirthdate(birthdate);
        coach.setPhone(phone);
        coach.setExperience(experience);
        return coach;
    }
}
