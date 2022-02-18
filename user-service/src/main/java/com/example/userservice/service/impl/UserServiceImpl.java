package com.example.userservice.service.impl;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String getUserNameById() {
        return userRepository.getLoginById(1L).getLogin();
    }
}
