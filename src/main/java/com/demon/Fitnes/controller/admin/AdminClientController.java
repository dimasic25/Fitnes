package com.demon.Fitnes.controller.admin;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.service.ClientService;
import com.demon.Fitnes.service.RightService;
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
    private final RightService rightService;

    @Autowired
    public AdminClientController(ClientService clientService, RightService rightService) {
        this.clientService = clientService;
        this.rightService = rightService;
    }

    @GetMapping("/clients")
    public String getAllClients(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            List<Client> clients = clientService.getAllClients();
            model.addAttribute("clients", clients);
            return "clients";
        }
    }
}
