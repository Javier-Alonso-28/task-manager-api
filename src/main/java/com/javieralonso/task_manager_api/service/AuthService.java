package com.javieralonso.task_manager_api.service;

import com.javieralonso.task_manager_api.dto.request.LoginRequest;
import com.javieralonso.task_manager_api.dto.request.RegisterRequest;
import com.javieralonso.task_manager_api.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
