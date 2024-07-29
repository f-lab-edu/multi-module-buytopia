package com.zeroskill.userapi.exceptionhandler;

import com.zeroskill.userapi.dto.response.ApiResponse;
import com.zeroskill.userapi.exception.UserApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Order(1)
    @ExceptionHandler(UserApiException.class)
    public <T> ResponseEntity<ApiResponse<T>> handleUserApiException(UserApiException e) {
        return new ResponseEntity<>(ApiResponse.of(e.getCode(), e.getExtMsg()), e.getHttpStatusCode());
    }

    @Order(2)
    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<ApiResponse<T>> handleException(Exception e) {
        return new ResponseEntity<>(ApiResponse.of(HttpStatusCode.valueOf(500).toString(), "예상치 못한 에러 발생"), HttpStatusCode.valueOf(500));
    }
}
