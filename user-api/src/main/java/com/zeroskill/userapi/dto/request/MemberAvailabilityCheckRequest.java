package com.zeroskill.userapi.dto.request;

import com.zeroskill.userapi.exception.UserApiException;
import com.zeroskill.userapi.exception.ErrorType;
import com.zeroskill.userapi.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.zeroskill.userapi.util.Util.isValidEmail;

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
