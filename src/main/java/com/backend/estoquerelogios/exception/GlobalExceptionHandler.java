package com.backend.estoquerelogios.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JaExistenteException.class)
    public ResponseEntity<ErroResponse> handleJaExisteException(JaExistenteException ex) {
        ErroResponse erro = new ErroResponse("JA_CADASTRADO", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(erro);
    }

    @ExceptionHandler(CamposObrigatoriosException.class)
    public ResponseEntity<ErroResponse> handleCamposObrigatoriosException(CamposObrigatoriosException ex) {
        ErroResponse erro = new ErroResponse("CAMPOS_OBRIGATORIOS", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(erro);
    }

    @ExceptionHandler(NaoExistenteException.class)
    public ResponseEntity<ErroResponse> handleNaoExistenteException(NaoExistenteException ex) {
        ErroResponse erro = new ErroResponse("NAO_EXISTENTE", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ValorInvalidoException.class)
    public ResponseEntity<ErroResponse> handleValorInvalidoException(ValorInvalidoException ex) {
        ErroResponse erro = new ErroResponse("ENTRADA_INVÁLIDA", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(erro);
    }

    @ExceptionHandler(LoginIncorretoException.class)
    public ResponseEntity<ErroResponse> handleLoginIncorretoException(LoginIncorretoException ex) {
        ErroResponse erro = new ErroResponse("LOGIN_INCORRETO", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(erro);
    }


}
