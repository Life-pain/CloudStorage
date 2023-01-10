package ru.netology.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.dto.AuthRequest;
import ru.netology.dto.AuthResponse;
import ru.netology.services.AuthService;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity<>(new AuthResponse(authService.loginUser(authRequest)), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String token) {
        authService.logoutUser(token);
        return ResponseEntity.ok().build();
    }
}