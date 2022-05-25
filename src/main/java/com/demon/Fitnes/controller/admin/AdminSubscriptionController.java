package com.demon.Fitnes.controller.admin;

import com.demon.Fitnes.model.Subscription;
import com.demon.Fitnes.service.RightService;
import com.demon.Fitnes.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminSubscriptionController {

    private final SubscriptionService subscriptionService;
    private final RightService rightService;

    @Autowired
    public AdminSubscriptionController(SubscriptionService subscriptionService, RightService rightService) {
        this.subscriptionService = subscriptionService;
        this.rightService = rightService;
    }

    @GetMapping("/subs")
    public String findAllSubs(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            List<Subscription> subscriptions = subscriptionService.getAllSubs();
            model.addAttribute("subs", subscriptions);
            return "subscriptions";
        }
    }

    @GetMapping(value = {"/subs/add"})
    public String showAddSub(Model model, HttpSession session) {

        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            Subscription subscription = new Subscription();

            model.addAttribute("add", true);
            model.addAttribute("sub", subscription);
            return "subscription-edit";
        }
    }

    @PostMapping(value = "/subs/add")
    public String addSubscription(Model model,
                                  @ModelAttribute("sub") Subscription subscription, HttpSession session) {
        try {
            if (!rightService.isUserAdmin(session, model)) {
                return "forbbiden";
            } else {
                subscriptionService.save(subscription);
                List<Subscription> subscriptions = subscriptionService.getAllSubs();

                model.addAttribute("subs", subscriptions);
                return "subscriptions";
            }
        } catch (Exception ex) {
//todo рефакторинг
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", true);
            return "subscription-edit";
        }
    }

    @GetMapping(value = {"/subs/{subId}/edit"})
    public String showEditSub(Model model, @PathVariable long subId, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            Subscription subscription;
            subscription = subscriptionService.getById(subId);

            model.addAttribute("add", false);
            model.addAttribute("sub", subscription);
            return "subscription-edit";
        }
    }

    @PostMapping(value = {"/subs/{subId}/edit"})
    public String updateSub(Model model,
                            @PathVariable long subId,
                            @ModelAttribute("sub") Subscription subscription, HttpSession session) {
        try {
            if (!rightService.isUserAdmin(session, model)) {
                return "forbbiden";
            } else {
                subscription.setId(subId);
                subscriptionService.update(subscription);

                List<Subscription> subscriptions = subscriptionService.getAllSubs();

                model.addAttribute("subs", subscriptions);
                return "subscriptions";
            }
        } catch (Exception ex) {
//todo рефакторинг
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "subscription-edit";
        }
    }

    @GetMapping(value = {"/subs/{subId}/delete"})
    public String deleteSub(Model model, @PathVariable long subId, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            subscriptionService.delete(subId);

            List<Subscription> subscriptions = subscriptionService.getAllSubs();

            model.addAttribute("subs", subscriptions);
            return "subscriptions";
        }
    }
}
