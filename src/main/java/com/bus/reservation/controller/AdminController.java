package com.bus.reservation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.reservation.model.Admin;
import com.bus.reservation.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {
            adminService.registerAdmin(admin);
            return ResponseEntity.ok().body(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("success", false, "message", "Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody Admin admin) {
        boolean isAuthenticated = adminService.authenticateAdmin(admin.getUsername(), admin.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Login successful"));
        } else {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Invalid username or password"));
        }
    }
}
