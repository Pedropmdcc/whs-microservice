package com.whs.supplier.api.dto.errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super("The supplier with the id " + msg + " doesn\'t exist.");
    }
}