package com.lucic.albumtracker.controller;

import com.lucic.albumtracker.entity.UserEntity;
import com.lucic.albumtracker.exception.SignUpException;
import com.lucic.albumtracker.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping
public class AuthenticationController {
    private final UserServiceImpl userService;
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model, UserEntity user) {
        if (error != null) {
            model.addAttribute("errorMessage", "Bad credentials");
        }
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") UserEntity user, Model model) {
        try {
            userService.registerUser(user);
            return "/login";
        } catch (SignUpException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/signup";
        }
    }

}
