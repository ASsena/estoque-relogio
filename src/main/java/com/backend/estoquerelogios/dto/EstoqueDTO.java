package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.Estoque;

public class EstoqueDTO {

    private Long id;
    private Long produtoId;
    private String produtoDescricao;
    private Long depositoId;
    private Integer quantidade;

    public EstoqueDTO(Long id, Long produtoId, String produtoDescricao, Long depositoId, Integer quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.produtoDescricao = produtoDescricao;
        this.depositoId = depositoId;
        this.quantidade = quantidade;
    }

    public EstoqueDTO(Estoque estoque) {
        this.id = estoque.getId();
        this.produtoId = estoque.getProduto().getId();
        this.produtoDescricao = estoque.getProduto().getDescricao();
        this.depositoId = estoque.getDeposito().getId();
        this.quantidade = estoque.getQuantidade();
    }

    public EstoqueDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }

    public Long getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Long depositoId) {
        this.depositoId = depositoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
