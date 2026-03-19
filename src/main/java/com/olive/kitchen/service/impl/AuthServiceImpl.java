package com.olive.kitchen.service.impl;

import com.olive.kitchen.dto.LoginRequest;
import com.olive.kitchen.dto.LoginResponse;
import com.olive.kitchen.entity.UserEntity;
import com.olive.kitchen.repository.UserRepository;
import com.olive.kitchen.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        // ⚠️ sementara plain text (nanti kita upgrade ke BCrypt)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return LoginResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}