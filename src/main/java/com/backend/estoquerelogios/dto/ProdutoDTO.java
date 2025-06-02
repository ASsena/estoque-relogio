package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.Produto;

import java.math.BigDecimal;

public class ProdutoDTO {

    private Long id;
    private String codigo;
    private String descricao;
    private BigDecimal precoUnitario;
    private String nome;

    public ProdutoDTO(Long id, String codigo, String descricao, BigDecimal precoUnitario, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.nome = nome;
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.codigo = produto.getCodigo();
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecoUnitario();
        this.nome = produto.getNome();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

