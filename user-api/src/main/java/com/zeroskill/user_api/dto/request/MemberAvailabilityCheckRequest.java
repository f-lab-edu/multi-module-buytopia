package com.zeroskill.user_api.dto.request;

import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.exception.ErrorType;
import com.zeroskill.user_api.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.zeroskill.user_api.util.Util.isValidEmail;

public record MemberAvailabilityCheckRequest(
        String loginId,
        String email
) implements Check {
    private static final Logger logger = LogManager.getLogger(MemberAvailabilityCheckRequest.class);
    @Override
    public boolean check() {
        if (loginId == null || loginId.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }
        if (email == null || email.isEmpty()) {
            throw new BuytopiaException(ErrorType.EMPTY_FIELD, logger::error);
        }
        if (!isValidEmail(email)) {
            throw new BuytopiaException(ErrorType.INVALID_EMAIL_FORMAT, logger::error);
        }
        return true;
    }
}
