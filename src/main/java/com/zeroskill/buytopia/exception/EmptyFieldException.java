package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String code, String message) {
        super(message);
        this.code = code;
    }

    private final String code;
}