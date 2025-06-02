package com.backend.estoquerelogios.exception;

import org.springframework.http.HttpStatus;

public class NaoExistenteException extends RuntimeException {
    private final HttpStatus status;

    public NaoExistenteException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
