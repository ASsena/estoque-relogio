package com.backend.estoquerelogios.exception;

import org.springframework.http.HttpStatus;

public class CamposObrigatoriosException extends RuntimeException {
    private final HttpStatus status;

    public CamposObrigatoriosException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
