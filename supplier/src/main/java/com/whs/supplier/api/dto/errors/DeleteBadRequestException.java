package com.whs.supplier.api.dto.errors;

public class DeleteBadRequestException extends RuntimeException {
    public DeleteBadRequestException(String msg) {
        super("The supplier with the id " + msg + " doesn\'t exist.");
    }
}