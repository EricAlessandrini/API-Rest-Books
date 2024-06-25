package com.ega.books.exception.exceptions;

import lombok.Getter;

@Getter
public class MappingException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public MappingException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
