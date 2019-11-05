package com.whs.supplier.api.dto.errors;

public class DuplicateRequestException extends RuntimeException {
    public DuplicateRequestException(String msg) {
        super("This supplier already exists. " + msg);
    }
}
