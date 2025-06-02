package com.backend.estoquerelogios.exception;

import org.springframework.http.HttpStatus;

public class ValorInvalidoException extends RuntimeException {
    private final HttpStatus status;

    public ValorInvalidoException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
