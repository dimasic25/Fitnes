package com.demon.Fitnes.controller.admin;

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
@RequestMapping("/admin/schedule")
public class AdminScheduleController {

    private final ScheduleService scheduleService;
    private final RightService rightService;

    @Autowired
    public AdminScheduleController(ScheduleService scheduleService, RightService rightService) {
        this.scheduleService = scheduleService;
        this.rightService = rightService;
    }

    @GetMapping
    public String getAllSchedules(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            List<Schedule> schedules = scheduleService.getAllSchedules();
            model.addAttribute("schedules", schedules);
            return "schedules";
        }
    }
}
