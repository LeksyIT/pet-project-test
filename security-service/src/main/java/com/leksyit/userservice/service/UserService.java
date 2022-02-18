package com.leksyit.userservice.service;

import com.leksyit.userservice.dto.UserRegistrationDto;
import com.leksyit.userservice.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    String getUserName();
}
