package com.demon.Fitnes.controller.admin;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.model.Subscription;
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
@RequestMapping("/admin/services")
public class AdminServiceController {

    private final RightService rightService;
    private final ServService servService;

    @Autowired
    public AdminServiceController(RightService rightService, ServService servService) {
        this.rightService = rightService;
        this.servService = servService;
    }

    @GetMapping
    public String findAllSubs(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            List<Service> services = servService.getServices();
            model.addAttribute("services", services);
            return "services";
        }
    }
}
