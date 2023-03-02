package com.example.fiode123.service;

import com.example.fiode123.config.UserAuthorDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserAuthorDTO extractUser(Authentication authentication) {
        UserAuthorDTO userAuthorDTO = (UserAuthorDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userAuthorDTO;
    }
}
