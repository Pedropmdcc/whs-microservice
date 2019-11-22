package com.whs.warehouse.api.dto.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> customHandleNotFound(final Exception ex, final WebRequest request) {
        final ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NO_CONTENT.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ErrorResponse> customHandleDuplicated(final Exception ex, final WebRequest request) {
        final ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DeleteBadRequestException.class)
    public ResponseEntity<ErrorResponse> customHandleDeleteBadRequest(final Exception ex, final WebRequest request) {
        final ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeightLimitsException.class)
    public ResponseEntity<ErrorResponse> customHandleSaveBadRequest(final Exception ex, final WebRequest request) {
        final ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
