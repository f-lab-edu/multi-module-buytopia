package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class PasswordMissMatchException extends RuntimeException {
    public PasswordMissMatchException(String code) {
        super(ErrorType.PASSWORD_MISS_MATCH_MSG.getData());
        this.code = code;
    }

    private final String code;
}
