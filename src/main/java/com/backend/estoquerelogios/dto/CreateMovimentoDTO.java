package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.TipoMovimento;
import jakarta.validation.constraints.NotNull;

public class CreateMovimentoDTO {

    @NotNull
    private Long produtoId;

    @NotNull
    private TipoMovimento tipo;

    @NotNull
    private Integer quantidade;

    private Long origemId;
    private Long destinoId;

    public CreateMovimentoDTO() {}

    public CreateMovimentoDTO(Long produtoId, TipoMovimento tipo, Integer quantidade, Long origemId, Long destinoId) {
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.origemId = origemId;
        this.destinoId = destinoId;
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
}

