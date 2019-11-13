package com.whs.favorite.api.dto.error;

public class DeleteBadRequestException extends RuntimeException {
    public DeleteBadRequestException(String resource) {
        super("The material with the id " + resource + " doesn\'t exist.");
    }
}