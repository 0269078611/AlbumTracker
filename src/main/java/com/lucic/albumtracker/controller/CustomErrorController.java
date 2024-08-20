package com.lucic.albumtracker.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class CustomErrorController implements ErrorController {

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        model.addAttribute("errorCode", "403");
        model.addAttribute("errorMessage", "You do not have permission to access this page");
        return "error";
    }
    @GetMapping("/notFound")
    public String notFound(Model model) {
        model.addAttribute("errorCode", "404");
        model.addAttribute("errorMessage", "Page not found");
        return "error";
    }

    @GetMapping("/serverError")
    public String serverError(Model model) {
        model.addAttribute("errorCode", "500");
        model.addAttribute("errorMessage", "Internal server error");
        return "error";
    }

    // Handle Bad Request (400)
    @GetMapping("/badRequest")
    public String badRequest(Model model) {
        model.addAttribute("errorCode", "400");
        model.addAttribute("errorMessage", "Bad request");
        return "error";
    }
}
