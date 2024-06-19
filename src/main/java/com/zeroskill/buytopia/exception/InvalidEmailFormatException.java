package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String code) {
        super(ErrorType.INVALID_EMAIL_FORMAT_MSG.getData());
        this.code = code;
    }

    private final String code;
}