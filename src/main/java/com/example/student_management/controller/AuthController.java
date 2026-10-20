
package com.example.student_management.controller;

import com.example.student_management.entity.User;
import com.example.student_management.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registeredUser = authService.register(user);

        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "username", registeredUser.getUsername(),
                "email", registeredUser.getEmail()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        String token = authService.login(username, password);

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "token", token
        ));
    }
}
