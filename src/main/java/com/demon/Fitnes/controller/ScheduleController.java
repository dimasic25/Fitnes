package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Schedule;
import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.service.RightService;
import com.demon.Fitnes.service.ScheduleService;
import com.demon.Fitnes.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final RightService rightService;
    private final ScheduleService scheduleService;
    private final ServService servService;

    @Autowired
    public ScheduleController(RightService rightService, ScheduleService scheduleService, ServService servService) {
        this.rightService = rightService;
        this.scheduleService = scheduleService;
        this.servService = servService;
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

    @GetMapping("/all")
    public String showSchedulesMenu(Model model, HttpSession session) {
        rightService.isUserAuthored(session, model);

        List<Service> services = servService.getServices();
        model.addAttribute("services", services);
        return "schedules-menu";
    }

    @GetMapping("/{serviceId}")
    public String showServiceSchedules(Model model, HttpSession session, @PathVariable Long serviceId) {
        rightService.isUserAuthored(session, model);

        List<Schedule> schedules = scheduleService.getSchedulesByService(serviceId);
        model.addAttribute("schedules", schedules);
        return "schedules";
    }
}
