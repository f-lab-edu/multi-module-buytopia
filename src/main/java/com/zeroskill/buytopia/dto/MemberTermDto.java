package com.zeroskill.buytopia.dto;

import com.zeroskill.buytopia.entity.MemberTerm;

import java.time.LocalDateTime;

public record MemberTermDto(
        Long id,
        Long memberId,
        Long termId,
        boolean agreed,
        LocalDateTime agreedDate
) {
    public static MemberTermDto of(MemberTerm memberTerm) {
        return new MemberTermDto(
                memberTerm.getId(),
                memberTerm.getMember().getId(),
                memberTerm.getTerm().getId(),
                memberTerm.isAgreed(),
                memberTerm.getAgreedDate()
        );
    }
}
