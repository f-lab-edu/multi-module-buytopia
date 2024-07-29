package com.zeroskill.userapi.dto;

import java.time.LocalDateTime;

public record AgreementDto(
        String loginId,
        String termTitle,
        String termVersion,
        boolean agreed,
        LocalDateTime agreedDate
) {
    public static AgreementDto of(com.zeroskill.userapi.entity.Agreement agreement) {
        return new AgreementDto(
                agreement.getMember().getLoginId(),
                agreement.getTerm().getId().getTitle(),
                agreement.getTerm().getId().getVersion(),
                agreement.isAgreed(),
                agreement.getAgreedDate()
        );
    }
}
