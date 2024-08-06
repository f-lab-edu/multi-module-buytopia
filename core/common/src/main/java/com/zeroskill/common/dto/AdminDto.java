package com.zeroskill.common.dto;

import jakarta.persistence.Column;

public record AdminDto(
        Long id,
        String loginId,
        String name,
        String email,
        String password
) {
    public static AdminDto hashPassword(AdminDto dto, String hashedPassword) {
        return new AdminDto(dto.id, dto.loginId, dto.name, dto.email, hashedPassword);
    }
}
