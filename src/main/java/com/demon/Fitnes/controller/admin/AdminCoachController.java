package com.demon.Fitnes.controller.admin;

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
@RequestMapping("/admin/coaches")
public class AdminCoachController {

    private final CoachService coachService;
    private final RightService rightService;

    @Autowired
    public AdminCoachController(CoachService coachService, RightService rightService) {
        this.coachService = coachService;
        this.rightService = rightService;
    }

    @GetMapping
    public String getAllCoaches(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            List<Coach> coaches = coachService.getCoaches();
            model.addAttribute("coaches", coaches);
            return "coaches";
        }
    }
}
