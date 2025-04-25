package com.grepp.nbe561team01.infra.error.exceptions;

public class PasswordDuplicatedException extends RuntimeException {

    public PasswordDuplicatedException(String message) {
        super(message);
    }
}
