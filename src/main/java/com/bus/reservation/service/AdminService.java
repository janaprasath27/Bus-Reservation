package com.bus.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bus.reservation.model.Admin;
import com.bus.reservation.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin registerAdmin(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        // Encrypt the password
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        // Save admin to the database
        return adminRepository.save(admin);
    }

    public boolean authenticateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        return admin != null && passwordEncoder.matches(password, admin.getPassword());
    }
}

