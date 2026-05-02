package com.javieralonso.task_manager_api.service.impl;

import com.javieralonso.task_manager_api.dto.request.LoginRequest;
import com.javieralonso.task_manager_api.dto.request.RegisterRequest;
import com.javieralonso.task_manager_api.dto.response.AuthResponse;
import com.javieralonso.task_manager_api.entity.Role;
import com.javieralonso.task_manager_api.entity.User;
import com.javieralonso.task_manager_api.repository.UserRepository;
import com.javieralonso.task_manager_api.security.JwtService;
import com.javieralonso.task_manager_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("El username ya está en uso");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, "Bearer", user.getUsername(), user.getEmail(), user.getRole().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, "Bearer", user.getUsername(), user.getEmail(), user.getRole().name());
    }
}