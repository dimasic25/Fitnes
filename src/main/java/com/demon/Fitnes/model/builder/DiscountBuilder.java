package com.demon.Fitnes.model.builder;

import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.model.Service;

public final class DiscountBuilder {
    private Long id;
    private Service service;
    private Integer amount;
    private Integer numberVisits;
    private String clientLogin;

    private DiscountBuilder() {
    }

    public static DiscountBuilder aDiscount() {
        return new DiscountBuilder();
    }

    public DiscountBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public DiscountBuilder service(Service service) {
        this.service = service;
        return this;
    }

    public DiscountBuilder amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public DiscountBuilder numberVisits(Integer numberVisits) {
        this.numberVisits = numberVisits;
        return this;
    }

    public DiscountBuilder clientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
        return this;
    }

    public Discount build() {
        Discount discount = new Discount();
        discount.setId(id);
        discount.setService(service);
        discount.setAmount(amount);
        discount.setNumberVisits(numberVisits);
        discount.setClientLogin(clientLogin);
        return discount;
    }
}
