package com.example.fiode123.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AuthRequest {

    @NotNull(message = "Email cannot null!")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Email is invalid!")
    private String email;

    @JsonProperty("password")
    @NotNull @Length(min = 5, max = 50)
    private String password;
}
