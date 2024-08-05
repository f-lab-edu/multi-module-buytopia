package com.zeroskill.admin_api.controller;

import com.zeroskill.common.dto.response.ApiResponse;
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
    public void addAdmin(@RequestBody Admin admin) {
        adminRepository.save(admin);
    }

    @GetMapping
    public ApiResponse<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return new ApiResponse<>(null, null, admins);
    }
}
