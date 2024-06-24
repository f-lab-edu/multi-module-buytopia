package com.zeroskill.buytopia.exceptionhandler;

import com.zeroskill.buytopia.dto.response.ApiResponse;
import com.zeroskill.buytopia.exception.DataNotFoundException;
import com.zeroskill.buytopia.exception.EmptyFieldException;
import com.zeroskill.buytopia.exception.InvalidEmailFormatException;
import com.zeroskill.buytopia.exception.PasswordMissMatchException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordMissMatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ApiResponse<T> handlePasswordMismatchException(PasswordMissMatchException e, HttpServletResponse response) {
        return ApiResponse.of(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(EmptyFieldException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ApiResponse<T> handleEmptyFieldException(EmptyFieldException e) {
        return ApiResponse.of(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ApiResponse<T> handleInvalidEmailFormatException(InvalidEmailFormatException e) {
        return ApiResponse.of(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ApiResponse<T> handleDuplicateEntityException(DataNotFoundException e) {
        return ApiResponse.of(e.getCode(), e.getMessage());
    }
}
