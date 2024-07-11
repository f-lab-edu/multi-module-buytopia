package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.BuytopiaException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public record AgreeToTermsRequest(
        String loginId,
        List<Long> termIds
) implements Check {
    private static final Logger logger = LogManager.getLogger(AgreeToTermsRequest.class);
    @Override
    public boolean check() {
        if(loginId == null || loginId.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }
        if (termIds == null || termIds.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }
        return true;
    }
}