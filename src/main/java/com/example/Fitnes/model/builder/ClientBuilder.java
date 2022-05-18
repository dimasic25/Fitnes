package com.example.Fitnes.model.builder;

import com.example.Fitnes.model.Client;

import java.util.Date;

public final class ClientBuilder {
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthdate;
    private Integer numberSubs;
    private Double price;

    private ClientBuilder() {
    }

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    public ClientBuilder login(String login) {
        this.login = login;
        return this;
    }

    public ClientBuilder password(String password) {
        this.password = password;
        return this;
    }

    public ClientBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ClientBuilder middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ClientBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ClientBuilder birthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public ClientBuilder numberSubs(Integer numberSubs) {
        this.numberSubs = numberSubs;
        return this;
    }

    public ClientBuilder price(Double price) {
        this.price = price;
        return this;
    }

    public Client build() {
        Client client = new Client();
        client.setLogin(login);
        client.setPassword(password);
        client.setFirstName(firstName);
        client.setMiddleName(middleName);
        client.setLastName(lastName);
        client.setBirthdate(birthdate);
        client.setNumberSubs(numberSubs);
        client.setPrice(price);
        return client;
    }
}
