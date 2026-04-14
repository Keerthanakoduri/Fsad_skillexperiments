package com.kluniversity.jwt.config;

import com.kluniversity.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        // Seed default users on application startup
        userService.register("admin",    "admin123",    "ROLE_ADMIN");
        userService.register("employee", "employee123", "ROLE_EMPLOYEE");
        System.out.println("========================================");
        System.out.println("  Default users seeded:");
        System.out.println("  ADMIN    -> username: admin    | password: admin123");
        System.out.println("  EMPLOYEE -> username: employee | password: employee123");
        System.out.println("========================================");
    }
}
