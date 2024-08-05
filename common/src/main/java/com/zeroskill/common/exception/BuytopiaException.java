package com.zeroskill.common.exception;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;

import java.util.function.Consumer;

@Getter
public class BuytopiaException extends RuntimeException {
    public BuytopiaException() {
        this.code = "";
        this.intMsg = "";
        this.extMsg = "";
        this.httpStatusCode = null;
        this.LogLevel = null;
    }

    public BuytopiaException(ErrorType errorType, Consumer<String> logger) {
        super(errorType.getIntMsg());
        this.code = errorType.getCode();
        this.intMsg = errorType.getIntMsg();
        this.extMsg = errorType.getExtMsg();
        this.httpStatusCode = errorType.getHttpStatusCode();
        this.LogLevel = null;

        String msg = String.format("Exception occurred: code=%s, msg=%s, httpStatus=%s",
                errorType.getCode(), errorType.getIntMsg(), errorType.getHttpStatusCode());
        logger.accept(msg);
    }

    private final String code;
    private final String intMsg;
    private final String extMsg;
    private final HttpStatusCode httpStatusCode;
    private final LogLevel LogLevel;
}