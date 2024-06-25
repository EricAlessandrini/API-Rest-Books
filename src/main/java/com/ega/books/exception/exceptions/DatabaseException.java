package com.ega.books.exception.exceptions;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class DatabaseException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public DatabaseException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
