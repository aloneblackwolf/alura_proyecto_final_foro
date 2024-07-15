package com.example.foro_hub.controller;

import com.example.foro_hub.repository.UserRepository;
import com.example.foro_hub.model.User;
import com.example.foro_hub.repository.UserRepository;
import com.example.foro_hub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken loginData =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}