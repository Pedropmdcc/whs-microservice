package com.whs.warehouse.api.dto.errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String msg) {
        super("The material with the id " + msg + " doesn\'t exist.");
    }
}
