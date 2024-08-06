package com.zeroskill.common.dto;

import com.zeroskill.common.entity.Agreement;

import java.time.LocalDateTime;

public record AgreementDto(
        String loginId,
        String termTitle,
        String termVersion,
        boolean agreed,
        LocalDateTime agreedDate
) {
    public static AgreementDto of(Agreement agreement) {
        return new AgreementDto(
                agreement.getMember().getLoginId(),
                agreement.getTerm().getId().getTitle(),
                agreement.getTerm().getId().getVersion(),
                agreement.isAgreed(),
                agreement.getAgreedDate()
        );
    }
}
