package com.example.fiode123.controller;


import com.example.fiode123.model.User;
import com.example.fiode123.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder encoder;
    // lay tat ca user hoac theo username
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        try {
            List<User> users = new ArrayList<User>();

            if(username == null)
                userRepository.findAll().forEach(users::add);
            else
                userRepository.findByUsername(username).forEach(users::add);

            if(users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //tao user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            String password = passwordEncoder.encode(user.getPassword());
            User _user = userRepository
                    .save(new User( user.getUsername(), user.getAlias(), user.getAvatar(), user.getPhone(), user.getEmail(), user.getGender(), password, "user"));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //sua user
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setUsername(user.getUsername());
            _user.setAlias(user.getAlias());
            _user.setAvatar(user.getAvatar());
            _user.setPhone(user.getPhone());
            _user.setEmail(user.getEmail());
            _user.setGender(user.getGender());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //doi password
    @PutMapping("/users/{id}/change-password")
    public ResponseEntity<User> updatePassword(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // xoa user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // tim bang alias (co the lam search users)
    @GetMapping("/users/alias")
    public ResponseEntity<List<User>> findByAlias(@PathVariable("alias") String alias) {
        try {
            List<User> users = userRepository.findByAlias(alias);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
