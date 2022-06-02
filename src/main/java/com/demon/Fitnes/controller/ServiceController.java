package com.demon.Fitnes.controller;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.service.RightService;
import com.demon.Fitnes.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServService servService;
    private final RightService rightService;

    @Autowired
    public ServiceController(ServService servService, RightService rightService) {
        this.servService = servService;
        this.rightService = rightService;
    }

    @GetMapping
    public String showAllServices(Model model, HttpSession session) {
        rightService.isUserAuthored(session, model);

        List<Service> serviceList = servService.getServices();
        model.addAttribute("services", serviceList);
        return "services";
    }
}
