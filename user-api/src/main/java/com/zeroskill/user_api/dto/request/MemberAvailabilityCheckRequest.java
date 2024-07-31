package com.zeroskill.user_api.dto.request;

import com.zeroskill.user_api.exception.UserApiException;
import com.zeroskill.user_api.exception.ErrorType;
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
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }
        if (email == null || email.isEmpty()) {
            throw new UserApiException(ErrorType.EMPTY_FIELD, logger::error);
        }
        if (!isValidEmail(email)) {
            throw new UserApiException(ErrorType.INVALID_EMAIL_FORMAT, logger::error);
        }
        return true;
    }
}
