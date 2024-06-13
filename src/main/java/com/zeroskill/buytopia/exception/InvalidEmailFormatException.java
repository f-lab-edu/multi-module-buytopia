package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String code, String message) {
        super(message);
        this.code = code;
    }

    private final String code;
}