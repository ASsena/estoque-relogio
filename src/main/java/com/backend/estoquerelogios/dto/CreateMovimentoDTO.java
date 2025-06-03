package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.TipoMovimento;
import jakarta.validation.constraints.NotNull;

public class CreateMovimentoDTO {

    @NotNull(message = "Preencha o id do produto")
    private Long produtoId;

    @NotNull(message = "Preencha o tipo de movimentação")
    private TipoMovimento tipo;

    @NotNull(message = "Preencha a quantidade movimentada")
    private Integer quantidade;

    private Long origemId;
    private Long destinoId;

    private Long estoqueId;

    public CreateMovimentoDTO() {}

    public CreateMovimentoDTO(Long produtoId, TipoMovimento tipo, Integer quantidade, Long origemId, Long destinoId, Long estoqueId) {
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.origemId = origemId;
        this.destinoId = destinoId;
        this.estoqueId = estoqueId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public TipoMovimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimento tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getOrigemId() {
        return origemId;
    }

    public void setOrigemId(Long origemId) {
        this.origemId = origemId;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public Long getEstoqueId() {
        return estoqueId;
    }

    public void setEstoqueId(Long estoqueId) {
        this.estoqueId = estoqueId;
    }
}

