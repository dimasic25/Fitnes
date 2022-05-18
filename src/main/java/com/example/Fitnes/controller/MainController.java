package com.example.Fitnes.controller;

import com.example.Fitnes.model.Client;
import com.example.Fitnes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final ClientService clientService;

    @Autowired
    public MainController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        Client client = new Client();

        model.addAttribute("client", client);
        return "index";
    }

    @PostMapping("/verify")
    public String verifyClient(Model model, @ModelAttribute("client") Client client) {
        try {
            clientService.verifyClient(client);

            return "main";
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "index";
        }
    }

    @GetMapping("/verify")
    public String getMenu() {
        return "main";
    }

}
