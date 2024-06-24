package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.EmptyFieldException;
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
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }
        if (termIds == null || termIds.isEmpty()) {
            throw new EmptyFieldException(ErrorType.EMPTY_FIELD_CD.getData());
        }
        return true;
    }
}