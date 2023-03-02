package com.example.fiode123.model;

import lombok.Data;

@Data
public class TokenResponse {
    private String token;

    public TokenResponse() {}

    public TokenResponse(String token) {
        this.token = token;
    }
}