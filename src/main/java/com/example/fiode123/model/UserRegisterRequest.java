package com.example.fiode123.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterRequest {
    @NotNull(message = "Username cannot null!")
    @Length(min = 5, max = 50, message = "Username must between 5 - 50 character")
    private String username;
    @NotNull(message = "Email cannot null!")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Email is invalid!")
    private String email;
    @NotNull(message = "Password cannot null!")
    @Length(min = 8, max = 50, message = "Password must between 5 - 50 character")
    private String password;

}