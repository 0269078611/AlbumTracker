package com.lucic.albumtracker.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        model.addAttribute("errorCode", "403");
        model.addAttribute("errorMessage", "You do not have permission to access this page");
        return "error";
    }
}
