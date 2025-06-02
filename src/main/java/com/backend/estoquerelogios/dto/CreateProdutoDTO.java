package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateProdutoDTO {

    @NotBlank(message = "Preencha o campo do código")
    private String codigo;

    @NotBlank(message = "Preencha o campo da descrição")
    private String descricao;

    @NotNull(message = "Preencha o campo do preço")
    private BigDecimal precoUnitario;

    @NotNull(message = "Preencha o campo do nome")
    private String nome;


    public CreateProdutoDTO() {}

    public CreateProdutoDTO(String codigo, String descricao, BigDecimal precoUnitario, String nome) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

