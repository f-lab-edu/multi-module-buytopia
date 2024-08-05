package com.zeroskill.admin_api.controller;

import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminRepository adminRepository;

    @PostMapping
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminRepository.save(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return ResponseEntity.ok(admins);
    }
}
