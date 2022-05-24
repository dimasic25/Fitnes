package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.model.Subscription;
import com.demon.Fitnes.service.ClientService;
import com.demon.Fitnes.service.ServiceSubsriptionService;
import com.demon.Fitnes.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subs")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final ClientService clientService;
    private final ServiceSubsriptionService serviceSubsriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, ClientService clientService, ServiceSubsriptionService serviceSubsriptionService) {
        this.subscriptionService = subscriptionService;
        this.clientService = clientService;
        this.serviceSubsriptionService = serviceSubsriptionService;
    }

    @GetMapping
    public String findAllSubs(Model model) {
        List<Subscription> subscriptions = subscriptionService.getAllSubs();

        model.addAttribute("subs", subscriptions);
        return "subs-list";
    }

    @GetMapping("/{login}")
    public String findSubsByClient(Model model, @PathVariable String login) {
        List<Subscription> subscriptions = subscriptionService.getSubsByClient(login);
        Client client = clientService.getClientByLogin(login);

        model.addAttribute("client", client);
        model.addAttribute("subs", subscriptions);
        return "main";
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

    @GetMapping("/{login}/{subId}")
    public String showServiceSubPage(Model model, @PathVariable String login, @PathVariable long subId) {
        List<ServiceSubscription> services = serviceSubsriptionService.getServiceSubsBySub(subId);
        Client client = clientService.getClientByLogin(login);

        model.addAttribute("client", client);
        model.addAttribute("services", services);

        return "service-subs";
    }
}
