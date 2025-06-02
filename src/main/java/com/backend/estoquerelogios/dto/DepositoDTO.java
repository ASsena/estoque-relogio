package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.Deposito;

public class DepositoDTO {

    private Long id;
    private String nome;
    private String localizacao;

    public DepositoDTO(Long id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public DepositoDTO(Deposito deposito) {
        this.id = deposito.getId();
        this.nome = deposito.getNome();
        this.localizacao = deposito.getLocalizacao();
    }

    public DepositoDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

