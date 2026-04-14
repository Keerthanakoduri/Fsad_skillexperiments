package com.kluniversity.auth.service;

import com.kluniversity.auth.model.AuthDTOs.*;
import com.kluniversity.auth.model.User;
import com.kluniversity.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public ApiResponse register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            return new ApiResponse("Username already exists!", false);
        }
        User user = new User();
        user.setUsername(req.getUsername());
        // NOTE: In production, always hash the password (e.g. BCrypt)
        user.setPassword(req.getPassword());
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setPhone(req.getPhone());
        userRepository.save(user);
        return new ApiResponse("User registered successfully!", true);
    }

    public LoginResponse login(LoginRequest req) {
        Optional<User> userOpt = userRepository.findByUsername(req.getUsername());
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(req.getPassword())) {
            User user = userOpt.get();
            return new LoginResponse("Login successful!", user.getId(), user.getUsername());
        }
        return null;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
