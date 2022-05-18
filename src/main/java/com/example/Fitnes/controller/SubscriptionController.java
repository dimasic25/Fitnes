package com.example.Fitnes.controller;

import com.example.Fitnes.model.Subscription;
import com.example.Fitnes.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subs")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public String findAllSubs(Model model) {
        List<Subscription> subscriptions = subscriptionService.getAllSubs();

        model.addAttribute("subs", subscriptions);
        return "subs-list";
    }

    @GetMapping(value = {"/add"})
    public String showAddSub(Model model) {
        Subscription subscription = new Subscription();
        model.addAttribute("add", true);
        model.addAttribute("sub", subscription);

        return "sub-edit";
    }

    @PostMapping(value = "/add")
    public String addContact(Model model,
                             @ModelAttribute("sub") Subscription subscription) {
        try {
            subscriptionService.save(subscription);
            List<Subscription> subscriptions = subscriptionService.getAllSubs();

            model.addAttribute("subs", subscriptions);
            return "subs-list";
        } catch (Exception ex) {

            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "sub-edit";
        }
    }

    @GetMapping(value = {"/{subId}/edit"})
    public String showEditSub(Model model, @PathVariable long subId) {
        Subscription subscription;
            subscription = subscriptionService.getById(subId);

        model.addAttribute("add", false);
        model.addAttribute("sub", subscription);
        return "sub-edit";
    }

    @PostMapping(value = {"/{subId}/edit"})
    public String updateSub(Model model,
                                @PathVariable long subId,
                                @ModelAttribute("sub") Subscription subscription) {
        try {
            subscription.setId(subId);
            subscriptionService.update(subscription);

            List<Subscription> subscriptions = subscriptionService.getAllSubs();

            model.addAttribute("subs", subscriptions);
            return "subs-list";
        } catch (Exception ex) {

            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "sub-edit";
        }
    }

    @GetMapping(value = {"/{subId}/delete"})
    public String deleteSub(Model model, @PathVariable long subId) {
        subscriptionService.delete(subId);

        List<Subscription> subscriptions = subscriptionService.getAllSubs();

        model.addAttribute("subs", subscriptions);
        return "subs-list";
    }
}
