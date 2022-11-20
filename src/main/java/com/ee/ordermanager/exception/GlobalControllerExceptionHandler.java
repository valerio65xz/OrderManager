package com.ee.ordermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The exception handler for validation exceptions
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * This method maps all the exceptions coming from validation input errors into a more readable message
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BindException ex) {
        Set<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getValidationErrorMessage)
                .collect(Collectors.toSet());

        return createErrorResponseEntity(HttpStatus.BAD_REQUEST.value(), errors);
    }

    private ResponseEntity<ErrorResponse> createErrorResponseEntity(int status, Set<String> message){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(status);
        errorResponse.setMessage(message);

        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * This method parse the objectError object to get all the validation errors
     */
    private String getValidationErrorMessage(ObjectError objectError){
        String field = Optional.ofNullable(objectError)
                .map(ObjectError::getCodes)
                .map(codes -> codes[0].substring(codes[0].indexOf(".", codes[0].indexOf(".") + 1) + 1))
                .map(substring -> substring.concat(" "))
                .orElse("");

        return Optional.ofNullable(objectError)
                .map(ObjectError::getDefaultMessage)
                .map(field::concat)
                .orElse("");
    }

}