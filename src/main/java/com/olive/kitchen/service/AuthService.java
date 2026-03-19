package com.olive.kitchen.service;

import com.olive.kitchen.dto.LoginRequest;
import com.olive.kitchen.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}