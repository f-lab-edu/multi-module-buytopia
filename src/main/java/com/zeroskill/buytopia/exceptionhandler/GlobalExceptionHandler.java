package com.zeroskill.buytopia.exceptionhandler;

import com.zeroskill.buytopia.converter.ResponseConverter;
import com.zeroskill.buytopia.dto.response.ApiResponse;
import com.zeroskill.buytopia.exception.DuplicateMemberException;
import com.zeroskill.buytopia.exception.EmptyFieldException;
import com.zeroskill.buytopia.exception.InvalidEmailFormatException;
import com.zeroskill.buytopia.exception.PasswordMissMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(PasswordMissMatchException.class)
    @ResponseBody
    public <T> ResponseEntity<ApiResponse<T>> handlePasswordMismatchException(PasswordMissMatchException e) {
        return ResponseConverter.convertToBadRequest(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(EmptyFieldException.class)
    @ResponseBody
    public <T> ResponseEntity<ApiResponse<T>> handleEmptyFieldException(EmptyFieldException e) {
        return ResponseConverter.convertToBadRequest(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    @ResponseBody
    public <T> ResponseEntity<ApiResponse<T>> handleInvalidEmailFormatException(InvalidEmailFormatException e) {
        return ResponseConverter.convertToBadRequest(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(DuplicateMemberException.class)
    @ResponseBody
    public <T> ResponseEntity<ApiResponse<T>> handleDuplicateMemberException(DuplicateMemberException e) {
        return ResponseConverter.convertToBadRequest(e.getCode(), e.getMessage());
    }
}
