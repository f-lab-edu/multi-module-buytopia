package com.zeroskill.user_api.dto.request;

import com.zeroskill.common.entity.TermId;
import com.zeroskill.user_api.exception.UserApiException;
import com.zeroskill.user_api.exception.ErrorType;
import com.zeroskill.user_api.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public record AgreeToTermsRequest(
        String loginId,
        List<TermId> termIds
) implements Check {
    private static final Logger logger = LogManager.getLogger(AgreeToTermsRequest.class);
    @Override
    public boolean check() {
        if(loginId == null || loginId.isEmpty()) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }
        if (termIds == null || termIds.isEmpty()) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }
        return true;
    }
}