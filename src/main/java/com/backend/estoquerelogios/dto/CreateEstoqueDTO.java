package com.backend.estoquerelogios.dto;

import jakarta.validation.constraints.NotNull;

public class CreateEstoqueDTO {

    @NotNull
    private Long produtoId;

    @NotNull
    private Long depositoId;

    @NotNull
    private Integer quantidade;

    public CreateEstoqueDTO() {}

    public CreateEstoqueDTO(Long produtoId, Long depositoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.depositoId = depositoId;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
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
