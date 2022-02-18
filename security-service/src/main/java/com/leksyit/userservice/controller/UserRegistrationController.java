package com.leksyit.userservice.controller;

import com.leksyit.userservice.dto.UserRegistrationDto;
import com.leksyit.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        if (userService.save(registrationDto) == null) {
            return "redirect:/registration?duplicate";
        } else {
            return "redirect:/registration?success";
        }

    }
}
