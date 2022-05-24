package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.repository.DiscountRepository;
import com.demon.Fitnes.repository.ServiceRepository;
import com.demon.Fitnes.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository, ServiceRepository serviceRepository) {
        this.discountRepository = discountRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAllDiscounts();

        for (Discount discount:
                discounts) {
            Service service =  serviceRepository.findById(discount.getService().getId()).get();
            discount.setService(service);
        }
        return discounts;
    }

    @Override
    public List<Discount> getDiscountsByClient(String clientLogin) {
        List<Discount> discounts = discountRepository.findDiscountsByClient(clientLogin);

        for (Discount discount:
             discounts) {
            Service service =  serviceRepository.findById(discount.getService().getId()).get();
            discount.setService(service);
        }
        return discounts;
    }
}
