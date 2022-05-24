package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.service.ClientService;
import com.demon.Fitnes.service.DiscountService;
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
    private final ClientService clientService;

    @Autowired
    public DiscountController(DiscountService discountService, ClientService clientService) {
        this.discountService = discountService;
        this.clientService = clientService;
    }

    @GetMapping
    public String showClientDiscounts(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        Client client = clientService.getClientByLogin(login);

        List<Discount> discounts = discountService.getDiscountsByClient(login);

        model.addAttribute("client", client);
        model.addAttribute("discounts", discounts);
        return "discounts";
    }
}
