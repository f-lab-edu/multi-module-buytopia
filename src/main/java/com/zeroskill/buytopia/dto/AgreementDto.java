package com.zeroskill.buytopia.dto;

import java.time.LocalDateTime;

public record AgreementDto(
        Long id,
        Long memberId,
        Long termId,
        boolean agreed,
        LocalDateTime agreedDate
) {
    public static AgreementDto of(com.zeroskill.buytopia.entity.Agreement agreement) {
        return new AgreementDto(
                agreement.getId(),
                agreement.getMember().getId(),
                agreement.getTerm().getId(),
                agreement.isAgreed(),
                agreement.getAgreedDate()
        );
    }
}
