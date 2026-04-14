package com.kluniversity.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    // In-memory list to simulate employee records
    private List<Map<String, String>> employees = new ArrayList<>();
    private long idCounter = 1;

    /**
     * POST /admin/add
     * Add a new employee record — ADMIN only
     */
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Map<String, String> body) {
        Map<String, String> employee = new HashMap<>(body);
        employee.put("id", String.valueOf(idCounter++));
        employees.add(employee);
        return ResponseEntity.ok(Map.of(
            "message", "Employee added successfully by ADMIN",
            "employee", employee
        ));
    }

    /**
     * DELETE /admin/delete/{id}
     * Delete an employee — ADMIN only
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        boolean removed = employees.removeIf(e -> id.equals(e.get("id")));
        if (removed) {
            return ResponseEntity.ok(Map.of("message", "Employee " + id + " deleted by ADMIN"));
        }
        return ResponseEntity.status(404).body(Map.of("message", "Employee not found with id: " + id));
    }

    /**
     * GET /admin/employees
     * View all employees — ADMIN only
     */
    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(Map.of(
            "message", "Employee list fetched by ADMIN",
            "employees", employees
        ));
    }
}
