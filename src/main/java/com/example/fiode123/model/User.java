package com.example.fiode123.model;


import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(name = "users");
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    public String username;

    @Column(nullable = false, unique = true, length = 30)
    public String alias;

    @Column(nullable = false, unique = true, length = 300)
    public String avatar;

    @Column(nullable = false, unique = true, length = 12)
    public String phone;

    @Column(nullable = false, unique = true, length = 100)
    public String email;

    @Column(nullable = false)
    public Integer gender;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, unique = true, length = 50)
    public String role;

    public User(String username, String alias, String avatar, String phone, String email, Integer gender, String password, String role) {
        this.username = username;
        this.alias = alias;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + ", desc=" + ", published=" + "]";
    }
}
