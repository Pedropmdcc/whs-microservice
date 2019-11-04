package com.whs.customer.api.dto.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
        ErrorResponse errors = new ErrorResponse();
        errors.setStatus(HttpStatus.NO_CONTENT.value());
        errors.setMessage(ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> customHandleDuplicated(Exception ex, WebRequest request) {
        ErrorResponse errors = new ErrorResponse();
        errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errors.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
