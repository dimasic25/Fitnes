package com.demon.Fitnes.service;

import com.demon.Fitnes.model.Discount;

import java.util.List;

public interface DiscountService {

    List<Discount> getAllDiscounts();
    List<Discount> getDiscountsByClient(String clientLogin);
}
