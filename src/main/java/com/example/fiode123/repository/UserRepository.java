package com.example.fiode123.repository;

import java.util.List;

import com.example.fiode123.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    List<User> findByAlias(String alias);
}
