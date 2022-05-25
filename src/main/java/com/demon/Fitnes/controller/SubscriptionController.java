package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.model.Subscription;
import com.demon.Fitnes.service.RightService;
import com.demon.Fitnes.service.ServiceSubsriptionService;
import com.demon.Fitnes.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/subs")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final ServiceSubsriptionService serviceSubsriptionService;
    private final RightService rightService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, ServiceSubsriptionService serviceSubsriptionService, RightService rightService) {
        this.subscriptionService = subscriptionService;
        this.serviceSubsriptionService = serviceSubsriptionService;
        this.rightService = rightService;
    }

    @GetMapping
    public String findSubsByClient(Model model, HttpSession session) {
        if (!rightService.isUserAuthored(session, model)) {
            return "forbbiden";
        } else {
            String login = (String) session.getAttribute("login");
            List<Subscription> subscriptions = subscriptionService.getSubsByClient(login);
            model.addAttribute("subs", subscriptions);
            return "subscriptions";
        }
    }

    @GetMapping("/{subId}")
    public String showServiceSubPage(Model model, @PathVariable long subId, HttpSession session) {
        if (!rightService.isUserAuthored(session, model)) {
            return "forbbiden";
        } else {
            List<ServiceSubscription> services = serviceSubsriptionService.getServiceSubsBySub(subId);
            model.addAttribute("services", services);
            return "service-subs";
        }
    }
}
