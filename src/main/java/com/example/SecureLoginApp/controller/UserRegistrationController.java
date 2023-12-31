package com.example.SecureLoginApp.controller;

import com.example.SecureLoginApp.dto.UserRegistrationDto;
import com.example.SecureLoginApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto getUserRegistration() {
        return new UserRegistrationDto();
    }

    @GetMapping()
    public String registrationForm() {
        return "registration";
    }

    @PostMapping()
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        userService.saveUser(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
