package com.whs.warehouse.api.dto.errors;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super("TESTE:) :" + msg);
    }
}
