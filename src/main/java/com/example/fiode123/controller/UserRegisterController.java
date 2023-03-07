package com.example.fiode123.controller;

import com.example.fiode123.config.UserService;
import com.example.fiode123.model.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserRegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/register")
    public String register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest)
                .map(DfResponse::okEntity);
    }
}