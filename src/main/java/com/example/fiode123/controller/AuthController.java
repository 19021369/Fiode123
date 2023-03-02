package com.example.fiode123.controller;

import com.example.fiode123.model.AuthRequest;
import com.example.fiode123.model.TokenResponse;
import com.example.fiode123.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/api/user/authenticate")
    public TokenResponse login(@RequestBody @Valid AuthRequest request)  {
        return authService.authenticate(request);
    }

}
