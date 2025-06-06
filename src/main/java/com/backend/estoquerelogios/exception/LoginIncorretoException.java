package com.backend.estoquerelogios.exception;

import org.springframework.http.HttpStatus;

public class LoginIncorretoException extends RuntimeException {
    private final HttpStatus status;

    public LoginIncorretoException(String message) {
        super(message);
        this.status = HttpStatus.UNAUTHORIZED;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
