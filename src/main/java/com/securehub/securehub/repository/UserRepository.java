package com.securehub.securehub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securehub.securehub.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
}
