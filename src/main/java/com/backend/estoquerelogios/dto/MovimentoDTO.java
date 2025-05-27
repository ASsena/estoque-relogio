package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.TipoMovimento;

import java.time.LocalDateTime;

public class MovimentoDTO {

    private Long id;
    private Long produtoId;
    private String produtoDescricao;
    private TipoMovimento tipo;
    private Integer quantidade;
    private Long origemId;
    private Long destinoId;
    private LocalDateTime dataHora;

    public MovimentoDTO(Long id, Long produtoId, String produtoDescricao, TipoMovimento tipo, Integer quantidade, Long origemId, Long destinoId, LocalDateTime dataHora) {
        this.id = id;
        this.produtoId = produtoId;
        this.produtoDescricao = produtoDescricao;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.origemId = origemId;
        this.destinoId = destinoId;
        this.dataHora = dataHora;
    }

    public MovimentoDTO() {
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
