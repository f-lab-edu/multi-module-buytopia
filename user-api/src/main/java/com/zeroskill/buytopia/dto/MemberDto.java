package com.zeroskill.buytopia.dto;

import com.zeroskill.buytopia.entity.Grade;

public record MemberDto(
        Long id,
        String loginId,
        String name,
        String email,
        String password,
        Grade grade,
        AddressDto addressdto
) {
    public static MemberDto hashPassword(MemberDto dto, String hashedPassword) {
        return new MemberDto(dto.id, dto.loginId, dto.name, dto.email, hashedPassword, dto.grade, dto.addressdto);
    }
}
