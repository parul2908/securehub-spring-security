package com.securehub.securehub.service;

import org.springframework.stereotype.Service;
import com.securehub.securehub.dto.RegisterRequest;
import com.securehub.securehub.entity.User;
import com.securehub.securehub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service 
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request){
        if(userRepository.existsByUsername(request.username())){
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("USER");
        user.setEnabled(true);
        return userRepository.save(user);
    }
}
