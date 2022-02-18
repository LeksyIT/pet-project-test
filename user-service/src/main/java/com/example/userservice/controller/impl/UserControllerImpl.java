package com.example.userservice.controller.impl;

import com.example.userservice.controller.UserController;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @GetMapping("/loginById")
    public String getLogin(){
        return userService.getUserNameById();
    }
}
