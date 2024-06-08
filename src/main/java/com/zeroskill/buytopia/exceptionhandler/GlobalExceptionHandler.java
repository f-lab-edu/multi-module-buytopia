package com.zeroskill.buytopia.exceptionhandler;

import com.zeroskill.buytopia.exception.DuplicateMemberException;
import com.zeroskill.buytopia.exception.EmptyFieldException;
import com.zeroskill.buytopia.exception.InvalidEmailFormatException;
import com.zeroskill.buytopia.exception.PasswordMismatchException;
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

    @ResponseStatus
    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseBody
    public ResponseEntity<String> handlePasswordMismatchException(PasswordMismatchException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ResponseStatus
    @ExceptionHandler(EmptyFieldException.class)
    @ResponseBody
    public ResponseEntity<String> handleEmptyFieldException(EmptyFieldException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ResponseStatus
    @ExceptionHandler(InvalidEmailFormatException.class)
    @ResponseBody
    public ResponseEntity<String> handleInvalidEmailFormatException(InvalidEmailFormatException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ResponseStatus
    @ExceptionHandler(DuplicateMemberException.class)
    @ResponseBody
    public ResponseEntity<String> handleDuplicateMemberException(DuplicateMemberException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
