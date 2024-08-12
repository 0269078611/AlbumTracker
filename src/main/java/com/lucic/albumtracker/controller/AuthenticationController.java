package com.lucic.albumtracker.controller;
import com.lucic.albumtracker.dto.UserDTO;
import com.lucic.albumtracker.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Bad credentials");
        }
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "auth/signUp";
    }

    @PostMapping
    public String signUp(@ModelAttribute("user") UserDTO user) {
        userService.registerUser(user);
        return "redirect:/";
    }

}
