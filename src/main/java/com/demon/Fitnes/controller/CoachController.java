package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Coach;
import com.demon.Fitnes.service.CoachService;
import com.demon.Fitnes.service.RightService;
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
    private final RightService rightService;

    @Autowired
    public CoachController(CoachService coachService, RightService rightService) {
        this.coachService = coachService;
        this.rightService = rightService;
    }

    @GetMapping
    public String showCoachesPage(Model model, HttpSession session) {
        if (!rightService.isUserAuthored(session, model)) {
            return "forbbiden";
        } else {
            List<Coach> coaches = coachService.getCoaches();
            model.addAttribute("coaches", coaches);
            return "coaches";
        }
    }
}
