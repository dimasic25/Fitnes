package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Schedule;
import com.demon.Fitnes.service.RightService;
import com.demon.Fitnes.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final RightService rightService;
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(RightService rightService, ScheduleService scheduleService) {
        this.rightService = rightService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public String showClientSchedules(Model model, HttpSession session) {
        if (!rightService.isUserAuthored(session, model)) {
            return "forbbiden";
        } else {
            List<Schedule> schedules = scheduleService.getClientSchedules((String) session.getAttribute("login"));
            model.addAttribute("schedules", schedules);
            return "schedules";
        }
    }
}
