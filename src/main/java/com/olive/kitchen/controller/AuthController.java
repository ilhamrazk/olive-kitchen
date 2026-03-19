package com.olive.kitchen.controller;

import com.olive.kitchen.dto.LoginRequest;
import com.olive.kitchen.dto.LoginResponse;
import com.olive.kitchen.entity.UserEntity;
import com.olive.kitchen.repository.UserRepository;
import com.olive.kitchen.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/test-user")
    public List<UserEntity> testUser() {
        return userRepository.findAll();
    }
}