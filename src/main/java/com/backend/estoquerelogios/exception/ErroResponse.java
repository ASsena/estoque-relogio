package com.backend.estoquerelogios.exception;

public class ErroResponse {
    private String message;
    private String codigo;

    public ErroResponse(String codigo, String message) {
        this.message = message;
        this.codigo = codigo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
