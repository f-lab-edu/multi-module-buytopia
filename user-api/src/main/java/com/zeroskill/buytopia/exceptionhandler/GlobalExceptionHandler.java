package com.zeroskill.buytopia.exceptionhandler;

import com.zeroskill.buytopia.dto.response.ApiResponse;
import com.zeroskill.buytopia.exception.BuytopiaException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Order(1)
    @ExceptionHandler(BuytopiaException.class)
    public <T> ResponseEntity<ApiResponse<T>> handleBuytopiaException(BuytopiaException e) {
        return new ResponseEntity<>(ApiResponse.of(e.getCode(), e.getExtMsg()), e.getHttpStatusCode());
    }

    @Order(2)
    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<ApiResponse<T>> handleException(Exception e) {
        return new ResponseEntity<>(ApiResponse.of(HttpStatusCode.valueOf(500).toString(), "예상치 못한 에러 발생"), HttpStatusCode.valueOf(500));
    }
}
