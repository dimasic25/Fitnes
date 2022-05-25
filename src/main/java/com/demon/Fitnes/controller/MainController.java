package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.service.ClientService;
import com.demon.Fitnes.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller

public class MainController {

    private final ClientService clientService;
    private final RightService rightService;

    @Autowired
    public MainController(ClientService clientService, RightService rightService) {
        this.clientService = clientService;
        this.rightService = rightService;
    }

    @GetMapping(value = {"/", "/login"})
    public String showLoginForm(Model model) {
        Client client = new Client();

        model.addAttribute("client", client);
        return "index";
    }

    @PostMapping("/verify")
    public String verifyClient(Model model, @ModelAttribute("client") Client client, HttpSession session) {
        try {
            clientService.verifyClient(client);
            session.setAttribute("login", client.getLogin());

            if (rightService.isUserAdmin(session, model)) {
                return "redirect:/admin/subs";
            } else {
                return "redirect:/subs";
            }

        } catch (Exception e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "index";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("birthdate");
        return "register";
    }

    @PostMapping("/register")
    public String registerClient(Model model, @ModelAttribute("client") Client client, @ModelAttribute("birthdate") String birthdate, HttpSession session) {
        try {
            Date clientBirthdate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
            client.setBirthdate(clientBirthdate);
            clientService.registerClient(client);
            session.setAttribute("login", client.getLogin());

            if (rightService.isUserAdmin(session, model)) {
                return "redirect:/admin/subs";
            } else {
                return "redirect:/subs";
            }

        } catch (Exception e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "register";
        }
    }
}
