package com.zeroskill.admin_api.controller;

import com.zeroskill.admin_api.dto.request.AdminRegistrationRequest;
import com.zeroskill.admin_api.service.AdminService;
import com.zeroskill.common.dto.AdminDto;
import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.entity.Admin;
import com.zeroskill.common.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public void addAdmin(@RequestBody AdminRegistrationRequest request) {
        request.check();
        AdminDto adminDto = AdminRegistrationRequest.toAdminDto(request);
        adminService.register(adminDto);
    }

    @GetMapping
    public ApiResponse<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.findAll();
        return new ApiResponse<>(null, null, admins);
    }
}
