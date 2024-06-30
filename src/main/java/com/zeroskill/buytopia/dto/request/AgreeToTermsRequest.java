package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.FieldValidatable;

import java.util.List;

public record AgreeToTermsRequest(
        String loginId,
        List<Long> termIds
) implements FieldValidatable {
    @Override
    public boolean checkEmptyField() {
        if(loginId == null || loginId.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        if (termIds == null || termIds.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        return true;
    }
}