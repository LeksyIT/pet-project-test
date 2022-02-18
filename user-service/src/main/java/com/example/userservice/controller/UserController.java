package com.example.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface UserController {

    @GetMapping("/loginById")
    String getLogin();
}
