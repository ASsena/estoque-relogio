package com.backend.estoquerelogios.exception;

import org.springframework.http.HttpStatus;

public class JaExistenteException extends RuntimeException {
    private final HttpStatus status;

    public JaExistenteException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT; // 409
    }

    public HttpStatus getStatus() {
        return status;
    }
}

