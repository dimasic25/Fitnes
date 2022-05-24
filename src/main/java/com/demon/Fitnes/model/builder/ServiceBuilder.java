package com.demon.Fitnes.model.builder;

import com.demon.Fitnes.model.Service;

public final class ServiceBuilder {
    private Long id;
    private String name;
    private Double price;
    private String place;
    private String description;
    private ServiceBuilder() {
    }

    public static ServiceBuilder aService() {
        return new ServiceBuilder();
    }

    public ServiceBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ServiceBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ServiceBuilder price(Double price) {
        this.price = price;
        return this;
    }

    public ServiceBuilder place(String place) {
        this.place = place;
        return this;
    }

    public ServiceBuilder description(String description) {
        this.description = description;
        return this;
    }

    public Service build() {
        Service service = new Service();
        service.setId(id);
        service.setName(name);
        service.setPrice(price);
        service.setPlace(place);
        service.setDescription(description);
        return service;
    }
}
