package com.example.fiode123.service;

import com.example.fiode123.model.AuthRequest;
import com.example.fiode123.model.TokenResponse;
import com.example.fiode123.model.User;
import com.example.fiode123.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    //check
    public TokenResponse authenticate(AuthRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        List<User> user = userRepository.findByEmail(request.getEmail());

        try {
            if (user.isEmpty()) {
                String encodedPassword = user.getPassword();

                if(passwordEncoder.matches(String.valueOf(request.getPassword()), encodedPassword)) {
                    String token = jwtTokenUtil.generateAccessToken(user.getEmail(), user.getPassword(), roles);
                    return new TokenResponse(token);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}