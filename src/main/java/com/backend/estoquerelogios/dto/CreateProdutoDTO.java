package com.backend.estoquerelogios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateProdutoDTO {

    @NotBlank
    private String codigo;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal precoUnitario;

    @NotBlank
    private String unidadeMedida;

    public CreateProdutoDTO() {}

    public CreateProdutoDTO(String codigo, String descricao, BigDecimal precoUnitario, String unidadeMedida) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.unidadeMedida = unidadeMedida;
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

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}

