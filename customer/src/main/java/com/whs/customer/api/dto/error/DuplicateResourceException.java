package com.whs.customer.api.dto.error;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String resource) {
        super(resource);
    }
}
