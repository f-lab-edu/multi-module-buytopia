package com.zeroskill.buytopia.dto.request;

import com.zeroskill.buytopia.exception.ErrorType;
import com.zeroskill.buytopia.validation.Check;

import static com.zeroskill.buytopia.util.Util.isValidEmail;

public record MemberAvailabilityCheckRequest(
        String loginId,
        String email
) implements Check {
    @Override
    public boolean check() {
        if (loginId == null || loginId.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        if (email == null || email.isEmpty()) {
            throw ErrorType.EMPTY_FIELD.exception();
        }
        if (!isValidEmail(email)) {
            throw ErrorType.INVALID_EMAIL_FORMAT.exception();
        }
        return true;
    }
}
