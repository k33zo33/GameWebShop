package hr.k33zo.gamewebshop.controller;


import hr.k33zo.gamewebshop.model.UserLog;
import hr.k33zo.gamewebshop.service.UserLoggerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/loggerController")
public class LoggerController {

    private final UserLoggerService userLoggerService;

    public LoggerController(UserLoggerService userLoggerService) {
        this.userLoggerService = userLoggerService;
    }
    @GetMapping("/logs")
    public String showLogs(Model model) {
        List<UserLog> userLogList = userLoggerService.getAllLogs();
        model.addAttribute("logs", userLogList);
        return "logs";
    }

}
