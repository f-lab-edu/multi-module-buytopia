package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.FieldValidatable;

import java.util.List;

public record GetTermsByTermIdsRequest(
        List<Long> termIds
) implements FieldValidatable {

    @Override
    public boolean checkEmptyField() {
        if (termIds == null || termIds.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        return true;
    }
}