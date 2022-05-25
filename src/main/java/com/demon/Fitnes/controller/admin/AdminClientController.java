package com.demon.Fitnes.controller.admin;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminClientController {

    private final ClientService clientService;

    @Autowired
    public AdminClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String getAllClients(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        Client client = clientService.getClientByLogin(login);
        model.addAttribute("client", client);
        model.addAttribute("isAdmin", session.getAttribute("isAdmin"));

        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "clients";
    }
}
