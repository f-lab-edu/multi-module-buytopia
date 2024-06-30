package com.zeroskill.buytopia.exceptionhandler;

import com.zeroskill.buytopia.dto.response.ApiResponse;
import com.zeroskill.buytopia.exception.BuytopiaException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BuytopiaException.class)
    @ResponseBody
    public <T> ResponseEntity<ApiResponse<T>> handleBuytopiaException(BuytopiaException e) {
        return new ResponseEntity<>(ApiResponse.of(e.getCode(), e.getMsg()), e.getHttpStatusCode());
    }
}
