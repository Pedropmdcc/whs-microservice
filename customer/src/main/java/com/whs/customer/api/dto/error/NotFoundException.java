package com.whs.customer.api.dto.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resource) {
        super("Resource username not found: " + resource);
    }
}
