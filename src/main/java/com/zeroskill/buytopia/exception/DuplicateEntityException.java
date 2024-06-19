package com.zeroskill.buytopia.exception;

import lombok.Getter;

@Getter
public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String code) {
        super(ErrorType.DUPLICATE_ENTITY_MSG.getData());
        this.code = code;
    }

    private final String code;
}