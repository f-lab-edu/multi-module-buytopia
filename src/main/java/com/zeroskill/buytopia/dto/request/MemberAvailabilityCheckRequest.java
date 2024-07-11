package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.BuytopiaException;
import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.Check;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.zeroskill.buytopia.util.Util.isValidEmail;

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
