package com.whs.warehouse.api.dto.errors;

public class DuplicateRequestException extends RuntimeException {
    public DuplicateRequestException(String msg) {
        super("HELLO: " + msg);
    }
}
