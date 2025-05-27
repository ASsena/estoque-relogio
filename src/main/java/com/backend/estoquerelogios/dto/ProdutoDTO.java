package com.backend.estoquerelogios.dto;

import java.math.BigDecimal;

public class ProdutoDTO {

    private Long id;
    private String codigo;
    private String descricao;
    private BigDecimal precoUnitario;

    public ProdutoDTO(Long id, String codigo, String descricao, BigDecimal precoUnitario) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    public ProdutoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}

