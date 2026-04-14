package com.kluniversity.jwt.controller;

import com.kluniversity.jwt.model.AuthDTOs.*;
import com.kluniversity.jwt.model.User;
import com.kluniversity.jwt.security.JwtUtil;
import com.kluniversity.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * POST /api/auth/register
     * Register a new user with a given role (ROLE_ADMIN or ROLE_EMPLOYEE)
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        try {
            // Default role is ROLE_EMPLOYEE if not specified in a real app
            // For demo, include role in request body via a wrapper or default here
            String role = "ROLE_EMPLOYEE";
            User user = userService.register(req.getUsername(), req.getPassword(), role);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered: " + user.getUsername() + " with role: " + role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    /**
     * POST /api/auth/login
     * Validate credentials and return a JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Optional<User> userOpt = userService.findByUsername(req.getUsername());

        if (userOpt.isPresent() && userService.checkPassword(req.getPassword(), userOpt.get().getPassword())) {
            User user  = userOpt.get();
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(new AuthResponse(token, user.getUsername(), user.getRole(), "Login successful!"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password!");
    }
}
