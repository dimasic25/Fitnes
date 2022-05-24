package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.service.ClientService;
import com.demon.Fitnes.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServService servService;
    private final ClientService clientService;

    @Autowired
    public ServiceController(ServService servService, ClientService clientService) {
        this.servService = servService;
        this.clientService = clientService;
    }

    @GetMapping
    public String showAllServices(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        Client client = clientService.getClientByLogin(login);
        List<Service> serviceList = servService.getServices();

        model.addAttribute("client", client);
        model.addAttribute("services", serviceList);
        return "services";
    }
}
