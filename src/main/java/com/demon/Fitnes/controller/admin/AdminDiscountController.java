package com.demon.Fitnes.controller.admin;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.Discount;
import com.demon.Fitnes.service.ClientService;
import com.demon.Fitnes.service.DiscountService;
import com.demon.Fitnes.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/discounts")
public class AdminDiscountController {

    private final RightService rightService;
    private final DiscountService discountService;
    private final ClientService clientService;

    @Autowired
    public AdminDiscountController(RightService rightService, DiscountService discountService, ClientService clientService) {
        this.rightService = rightService;
        this.discountService = discountService;
        this.clientService = clientService;
    }

    @GetMapping
    public String findClientDiscounts(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            List<Discount> discounts = discountService.getClientDiscounts();

            model.addAttribute("discounts", discounts);
            return "discounts";
        }
    }

    @GetMapping(value = {"/add"})
    public String showAddSub(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            Discount discount = new Discount();

            List<Discount> discounts = discountService.getAllDiscounts();
            Set<String> logins = new HashSet<>();

            List<Client> clients = clientService.getAllClients();

            for (Client client:
                    clients) {
                logins.add(client.getLogin());
            }

            model.addAttribute("logins", logins);
            model.addAttribute("discount", discount);
            model.addAttribute("discounts", discounts);
            return "discount-form";
        }
    }

    @PostMapping(value = "/add")
    public String addSubscription(Model model,
                                  @ModelAttribute("discount") Discount newDiscount, HttpSession session) {
        try {
            if (!rightService.isUserAdmin(session, model)) {
                return "forbbiden";
            } else {
                discountService.save(newDiscount);
                List<Discount> discounts = discountService.getClientDiscounts();

                model.addAttribute("discounts", discounts);
                return "discounts";
            }
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            Discount discount = new Discount();

            List<Discount> discounts = discountService.getAllDiscounts();
            Set<String> logins = new HashSet<>();

            List<Client> clients = clientService.getAllClients();

            for (Client client:
                    clients) {
                logins.add(client.getLogin());
            }

            model.addAttribute("logins", logins);
            model.addAttribute("discount", discount);
            model.addAttribute("discounts", discounts);
            return "discount-form";
        }
    }

    @GetMapping("/{login}/{discountId}/delete")
    public String deleteDiscount(Model model, HttpSession session, @PathVariable String login, @PathVariable Long discountId) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            discountService.delete(login, discountId);

            return "redirect:/admin/discounts";
        }
    }
}
