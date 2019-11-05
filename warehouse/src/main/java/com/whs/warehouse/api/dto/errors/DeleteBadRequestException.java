package com.whs.warehouse.api.dto.errors;

public class DeleteBadRequestException extends RuntimeException {
    public DeleteBadRequestException(String msg) {
        super("The material with the id " + msg + " doesn\'t exist.");
    }
}