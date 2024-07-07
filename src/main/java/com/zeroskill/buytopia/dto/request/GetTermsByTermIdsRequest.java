package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.Check;

// TODO: purpose를 ENUM으로 처리
public record GetTermsByTermIdsRequest(
        String purpose
) implements Check {

    @Override
    public boolean check() {
        if (purpose == null || purpose.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        return true;
    }
}