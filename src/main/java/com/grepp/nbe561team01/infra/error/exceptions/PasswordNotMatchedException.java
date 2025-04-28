package com.grepp.nbe561team01.infra.error.exceptions;

public class PasswordNotMatchedException extends RuntimeException {

    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
