package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.Check;

import java.util.List;

public record AgreeToTermsRequest(
        String loginId,
        List<Long> termIds
) implements Check {
    @Override
    public boolean check() {
        if(loginId == null || loginId.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        if (termIds == null || termIds.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        return true;
    }
}