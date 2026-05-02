package com.javieralonso.task_manager_api.dto.response;

public record AuthResponse(
        String token,
        String tokenType,
        String username,
        String email,
        String role
) {}