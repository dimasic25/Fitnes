package com.demon.Fitnes.controller.admin;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.ServiceSubscription;
import com.demon.Fitnes.model.Subscription;
import com.demon.Fitnes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/subs")
public class AdminSubscriptionController {

    private final SubscriptionService subscriptionService;
    private final RightService rightService;
    private final ServiceSubsriptionService serviceSubsriptionService;
    private final ServService servService;
    private final ScheduleService scheduleService;
    private final ClientService clientService;

    @Autowired
    public AdminSubscriptionController(SubscriptionService subscriptionService, RightService rightService, ServiceSubsriptionService serviceSubsriptionService, ServService servService, ScheduleService scheduleService, ClientService clientService) {
        this.subscriptionService = subscriptionService;
        this.rightService = rightService;
        this.serviceSubsriptionService = serviceSubsriptionService;
        this.servService = servService;
        this.scheduleService = scheduleService;
        this.clientService = clientService;
    }

    @GetMapping
    public String findAllSubs(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            List<Subscription> subscriptions = subscriptionService.getAllSubs();
            model.addAttribute("subs", subscriptions);
            return "subscriptions";
        }
    }

    @GetMapping(value = {"/add"})
    public String showAddSub(Model model, HttpSession session) {

        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            Subscription subscription = new Subscription();

            Set<String> logins = new HashSet<>();

            List<Client> clients = clientService.getAllClients();

            for (Client client:
                    clients) {
                logins.add(client.getLogin());
            }

            model.addAttribute("logins", logins);
            model.addAttribute("add", true);
            model.addAttribute("sub", subscription);
            return "subscription-form";
        }
    }

    @PostMapping(value = "/add")
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
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            Set<String> logins = new HashSet<>();

            List<Client> clients = clientService.getAllClients();

            for (Client client:
                    clients) {
                logins.add(client.getLogin());
            }

            model.addAttribute("logins", logins);

            model.addAttribute("add", true);
            return "subscription-form";
        }
    }

    @GetMapping(value = {"/{subId}/edit"})
    public String showEditSub(Model model, @PathVariable long subId, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            Subscription subscription;
            subscription = subscriptionService.getById(subId);

            Set<String> logins = new HashSet<>();

            List<Client> clients = clientService.getAllClients();

            for (Client client:
                    clients) {
                logins.add(client.getLogin());
            }

            model.addAttribute("logins", logins);

            model.addAttribute("add", false);
            model.addAttribute("sub", subscription);
            return "subscription-form";
        }
    }

    @PostMapping(value = {"/{subId}/edit"})
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
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            Set<String> logins = new HashSet<>();

            List<Client> clients = clientService.getAllClients();

            for (Client client:
                    clients) {
                logins.add(client.getLogin());
            }

            model.addAttribute("logins", logins);

            model.addAttribute("add", false);
            return "subscription-form";
        }
    }

    @GetMapping(value = {"/{subId}/delete"})
    public String deleteSub(Model model, @PathVariable long subId, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            subscriptionService.delete(subId);

            return "redirect:/admin/subs";
        }
    }

    @GetMapping("/{subId}/services")
    public String showServiceSubPage(Model model, @PathVariable long subId, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            List<ServiceSubscription> services = serviceSubsriptionService.getServiceSubsBySub(subId);
            model.addAttribute("services", services);
            model.addAttribute("subId", subId);
            return "service-subs";
        }
    }

    @GetMapping(value = {"/{subId}/services/add"})
    public String showAddServiceForm(Model model, HttpSession session, @PathVariable Long subId) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            ServiceSubscription serviceSubscription = new ServiceSubscription();
            model.addAttribute("subser", serviceSubscription);

            List<Service> services = servService.getServices();
            model.addAttribute("services", services);

            List<Integer> groupNumbers = scheduleService.getGroupNumbers();
            model.addAttribute("groupNumbers", groupNumbers);

            model.addAttribute("add", true);
            model.addAttribute("subId", subId);
            return "service-sub-form";
        }
    }

    @PostMapping(value = {"/{subId}/services/add"})
    public String addServiceSubscription(Model model,
                                  @ModelAttribute("subser") ServiceSubscription newServiceSubscription, HttpSession session, @PathVariable Long subId) {
        try {
            if (!rightService.isUserAdmin(session, model)) {
                return "forbbiden";
            } else {
                Subscription subscription = new Subscription();
                subscription.setId(subId);
                newServiceSubscription.setSubscription(subscription);
                serviceSubsriptionService.save(newServiceSubscription);

                List<ServiceSubscription> services = serviceSubsriptionService.getServiceSubsBySub(subId);
                model.addAttribute("services", services);
                model.addAttribute("subId", subId);
                return "service-subs";
            }
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            ServiceSubscription serviceSubscription = new ServiceSubscription();
            model.addAttribute("subser", serviceSubscription);

            List<Service> services = servService.getServices();
            model.addAttribute("services", services);

            List<Integer> groupNumbers = scheduleService.getGroupNumbers();
            model.addAttribute("groupNumbers", groupNumbers);

            model.addAttribute("add", true);
            model.addAttribute("subId", subId);
            return "service-sub-form";
        }
    }

    @GetMapping(value = {"/{subId}/services/{serviceId}/edit"})
    public String showEditServiceForm(Model model, @PathVariable long subId, @PathVariable Long serviceId, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            try {
                ServiceSubscription serviceSubscription = serviceSubsriptionService.getServiceSub(subId, serviceId);

                List<Service> services = servService.getServices();
                model.addAttribute("services", services);

                List<Integer> groupNumbers = scheduleService.getGroupNumbers();
                model.addAttribute("groupNumbers", groupNumbers);

                model.addAttribute("add", false);
                model.addAttribute("subser", serviceSubscription);
                model.addAttribute("subId", subId);
                return "service-sub-form";
            } catch (Exception ex) {
                String errorMessage = ex.getMessage();
                model.addAttribute("errorMessage", errorMessage);

                List<ServiceSubscription> services = serviceSubsriptionService.getServiceSubsBySub(subId);
                model.addAttribute("services", services);
                model.addAttribute("subId", subId);
                return "service-subs";
            }
        }
    }

    @PostMapping(value = {"/{subId}/services/{serviceId}/edit"})
    public String updateServiceSub(Model model,
                                   @PathVariable long subId, @PathVariable Long serviceId,
                            @ModelAttribute("subser") ServiceSubscription editServiceSubscription, HttpSession session) throws Exception {
        try {
            if (!rightService.isUserAdmin(session, model)) {
                return "forbbiden";
            } else {
                Subscription subscription = new Subscription();
                subscription.setId(subId);
                editServiceSubscription.setSubscription(subscription);
                serviceSubsriptionService.update(editServiceSubscription);

                List<ServiceSubscription> services = serviceSubsriptionService.getServiceSubsBySub(subId);
                model.addAttribute("services", services);
                model.addAttribute("subId", subId);

                return "service-subs";
            }
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            ServiceSubscription serviceSubscription = serviceSubsriptionService.getServiceSub(subId, serviceId);

            List<Service> services = servService.getServices();
            model.addAttribute("services", services);

            List<Integer> groupNumbers = scheduleService.getGroupNumbers();
            model.addAttribute("groupNumbers", groupNumbers);

            model.addAttribute("add", false);
            model.addAttribute("subser", serviceSubscription);
            model.addAttribute("subId", subId);
            return "service-sub-form";
        }
    }

    @GetMapping("/{subId}/services/{serviceId}/delete")
    public String deleteService(Model model, HttpSession session, @PathVariable Long subId, @PathVariable Long serviceId) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            serviceSubsriptionService.delete(serviceId, subId);

            return "redirect:/admin/subs/" + subId + "/services";
        }
    }
}
