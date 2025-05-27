package com.backend.estoquerelogios.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateDepositorioDTO {

    @NotBlank
    private String nome;

    private String localizacao;

    public CreateDepositorioDTO() {}

    public CreateDepositorioDTO(String nome, String localizacao) {
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

