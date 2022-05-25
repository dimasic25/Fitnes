package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.service.DiscountService;
import com.demon.Fitnes.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;
    private final RightService rightService;

    @Autowired
    public DiscountController(DiscountService discountService, RightService rightService) {
        this.discountService = discountService;
        this.rightService = rightService;
    }

    @GetMapping
    public String showClientDiscounts(Model model, HttpSession session) {
        if (!rightService.isUserAuthored(session, model)) {
            return "forbbiden";
        } else {
            String login = (String) session.getAttribute("login");
            List<Discount> discounts = discountService.getDiscountsByClient(login);

            model.addAttribute("discounts", discounts);
            return "discounts";
        }
    }
}
