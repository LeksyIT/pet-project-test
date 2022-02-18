package com.leksyit.productservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-service")
public interface UserController {

    @GetMapping("/loginById")
    String getLogin();
}
