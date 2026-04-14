package com.kluniversity.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    /**
     * GET /employee/profile
     * Returns the profile of the currently authenticated employee.
     * Accessible by ROLE_EMPLOYEE and ROLE_ADMIN.
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        String username = authentication.getName();
        String role     = authentication.getAuthorities().iterator().next().getAuthority();

        return ResponseEntity.ok(Map.of(
            "message", "Profile fetched successfully",
            "username", username,
            "role",     role,
            "status",   "Active"
        ));
    }

    /**
     * GET /employee/dashboard
     * Returns a simple dashboard message.
     */
    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
            "message", "Welcome to the Employee Dashboard, " + authentication.getName() + "!",
            "access",  "EMPLOYEE / ADMIN"
        ));
    }
}
