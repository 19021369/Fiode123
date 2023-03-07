package com.example.fiode123.config;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.management.relation.Relation;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserAuthorDTO {
    private Integer userId;
    private List<String> roles;

    public Relation setUserId(Long id) {
    }
}