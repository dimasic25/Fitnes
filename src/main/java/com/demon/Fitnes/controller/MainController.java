package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller

public class MainController {

    private final ClientService clientService;

    @Autowired
    public MainController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = {"/", "/login"})
    public String showLoginForm(Model model) {
        Client client = new Client();

        model.addAttribute("client", client);
        return "index";
    }

    @PostMapping("/verify")
    public String verifyClient(Model model, @ModelAttribute("client") Client client) {
        try {
            clientService.verifyClient(client);
            model.addAttribute("client", client);

            return "redirect:/subs/" + client.getLogin();
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

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("birthdate");
        return "register";
    }

    @PostMapping("/register")
    public String registerClient(Model model, @ModelAttribute("client") Client client, @ModelAttribute("birthdate") String birthdate) {
        try {
            Date clientBirthdate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
            client.setBirthdate(clientBirthdate);
            clientService.registerClient(client);
            model.addAttribute("client", client);

            return "redirect:/subs/" + client.getLogin();
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "register";
        }
    }
}
