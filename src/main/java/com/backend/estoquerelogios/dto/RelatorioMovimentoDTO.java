package com.backend.estoquerelogios.dto;

import com.backend.estoquerelogios.entities.Movimento;
import com.backend.estoquerelogios.entities.Produto;
import com.backend.estoquerelogios.entities.TipoMovimento;

import java.time.LocalDateTime;

public class RelatorioMovimentoDTO{

    private String produto;
    private String tipo;
    private Integer quantidade;
    private String origem;
    private String destino;
    private LocalDateTime dataHora;

    public RelatorioMovimentoDTO(String produto, String tipo, Integer quantidade, String origem, String destino, LocalDateTime dataHora) {
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
    }

    public RelatorioMovimentoDTO(Movimento movimento) {
        this.produto = movimento.getProduto().getNome();
        this.tipo = movimento.getTipo().name();
        this.quantidade = movimento.getQuantidade();
        this.destino = movimento.getDestino().getDeposito().getNome();
        this.origem = movimento.getOrigem().getDeposito().getNome();
        this.dataHora = movimento.getDataHora();
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
