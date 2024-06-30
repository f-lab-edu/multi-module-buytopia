package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.Check;

import java.util.List;

public record GetTermsByTermIdsRequest(
        List<Long> termIds
) implements Check {

    @Override
    public boolean check() {
        if (termIds == null || termIds.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        return true;
    }
}