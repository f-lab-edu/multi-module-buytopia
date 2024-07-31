package com.zeroskill.user_api.dto;

import java.time.LocalDateTime;

public record AgreementDto(
        String loginId,
        String termTitle,
        String termVersion,
        boolean agreed,
        LocalDateTime agreedDate
) {
    public static AgreementDto of(com.zeroskill.user_api.entity.Agreement agreement) {
        return new AgreementDto(
                agreement.getMember().getLoginId(),
                agreement.getTerm().getId().getTitle(),
                agreement.getTerm().getId().getVersion(),
                agreement.isAgreed(),
                agreement.getAgreedDate()
        );
    }
}
