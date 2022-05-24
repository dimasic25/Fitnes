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

import javax.servlet.http.HttpSession;
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
    public String findSubsByClient(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        List<Subscription> subscriptions = subscriptionService.getSubsByClient(login);
        Client client = clientService.getClientByLogin(login);

        model.addAttribute("isAdmin", session.getAttribute("isAdmin"));
        model.addAttribute("client", client);
        model.addAttribute("subs", subscriptions);
        return "subscriptions";
    }

    @GetMapping("/{subId}")
    public String showServiceSubPage(Model model, @PathVariable long subId, HttpSession session) {
        String login = (String) session.getAttribute("login");
        List<ServiceSubscription> services = serviceSubsriptionService.getServiceSubsBySub(subId);
        Client client = clientService.getClientByLogin(login);

        model.addAttribute("client", client);
        model.addAttribute("services", services);

        return "service-subs";
    }
}
