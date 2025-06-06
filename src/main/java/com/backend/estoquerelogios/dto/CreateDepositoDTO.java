package com.backend.estoquerelogios.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateDepositoDTO {

    @NotBlank(message = "Preencha o campo de nome")
    private String nome;
    @NotBlank(message = "Preencha o campo de localização")
    private String localizacao;

    public CreateDepositoDTO() {}

    public CreateDepositoDTO(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}

