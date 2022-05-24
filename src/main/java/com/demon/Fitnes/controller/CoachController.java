package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.model.Coach;
import com.demon.Fitnes.service.ClientService;
import com.demon.Fitnes.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;
    private final ClientService clientService;

    @Autowired
    public CoachController(CoachService coachService, ClientService clientService) {
        this.coachService = coachService;
        this.clientService = clientService;
    }

    @GetMapping
    public String showCoachesPage(Model model, HttpSession session) {
        String login = (String) session.getAttribute("login");
        Client client = clientService.getClientByLogin(login);

        List<Coach> coaches = coachService.getCoaches();

        model.addAttribute("client", client);
        model.addAttribute("coaches", coaches);
        return "coaches";
    }
}
