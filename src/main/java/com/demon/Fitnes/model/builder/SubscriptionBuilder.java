package com.demon.Fitnes.model.builder;

import com.demon.Fitnes.model.Subscription;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class SubscriptionBuilder {
    private Long id;
    private String clientLogin;
    private Date startDate;
    private Date endDate;
    private boolean closed;
    private double price;

    private SubscriptionBuilder() {
    }

    public static SubscriptionBuilder aSubscription() {
        return new SubscriptionBuilder();
    }

    public SubscriptionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SubscriptionBuilder clientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
        return this;
    }

    public SubscriptionBuilder startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public SubscriptionBuilder endDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public SubscriptionBuilder closed(boolean closed) {
        this.closed = closed;
        return this;
    }

    public SubscriptionBuilder price(double price) {
        this.price = price;
        return this;
    }

    public Subscription build() {
        Subscription subscription = new Subscription();
        subscription.setId(id);
        subscription.setClientLogin(clientLogin);

        String start = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        subscription.setStartDate(start);

        String end = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
        subscription.setEndDate(end);

        subscription.setClosed(closed);
        subscription.setPrice(price);
        return subscription;
    }
}
