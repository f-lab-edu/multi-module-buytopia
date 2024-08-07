package com.zeroskill.common.exceptionhandler;

import com.zeroskill.common.dto.response.ApiResponse;
import com.zeroskill.common.exception.BuytopiaException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @Order(1)
    @ExceptionHandler(BuytopiaException.class)
    public <T> ResponseEntity<ApiResponse<T>> handleUserApiException(BuytopiaException e) {
        return new ResponseEntity<>(ApiResponse.of(e.getCode(), e.getExtMsg()), e.getHttpStatusCode());
    }

    @Order(2)
    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<ApiResponse<T>> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(ApiResponse.of(HttpStatusCode.valueOf(500).toString(), "예상치 못한 에러 발생"), HttpStatusCode.valueOf(500));
    }
}
