package com.example.foro_hub.controller;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}