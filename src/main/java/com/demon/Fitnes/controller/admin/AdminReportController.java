package com.demon.Fitnes.controller.admin;

import com.demon.Fitnes.model.Subscription;
import com.demon.Fitnes.service.ReportService;
import com.demon.Fitnes.service.RightService;
import com.demon.Fitnes.service.SubscriptionService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/admin/reports")
public class AdminReportController {

    private final ReportService reportService;
    private final RightService rightService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public AdminReportController(ReportService reportService, RightService rightService, SubscriptionService subscriptionService) {
        this.reportService = reportService;
        this.rightService = rightService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public String report(Model model, HttpSession session) {
        if (!rightService.isUserAdmin(session, model)) {
            return "forbbiden";
        } else {
            try {
                reportService.exportClientReport();
                reportService.exportServiceReport();

                model.addAttribute("successMessage", "Отчёты успешно составлены");
                List<Subscription> subscriptions = subscriptionService.getAllSubs();
                model.addAttribute("subs", subscriptions);

                return "subscriptions";
            } catch (FileNotFoundException e) {
                model.addAttribute("errorMessage", "Файл не найден!");
                List<Subscription> subscriptions = subscriptionService.getAllSubs();
                model.addAttribute("subs", subscriptions);

                return "subscriptions";
            } catch (JRException e) {
                model.addAttribute("errorMessage", "Ошибка при составлении отчёта!");
                List<Subscription> subscriptions = subscriptionService.getAllSubs();
                model.addAttribute("subs", subscriptions);

                return "subscriptions";
            }
        }
    }
}
