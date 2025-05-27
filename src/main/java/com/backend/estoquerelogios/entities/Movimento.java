package com.backend.estoquerelogios.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movements")
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimento tipo;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    private Estoque origem;

    @ManyToOne
    private Estoque destino;

    private LocalDateTime dataHora = LocalDateTime.now();

    public Movimento(Long id, Produto produto, TipoMovimento tipo, Integer quantidade, Estoque origem, Estoque destino, LocalDateTime dataHora) {
        this.id = id;
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
    }

    public Movimento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public Estoque getOrigem() {
        return origem;
    }

    public void setOrigem(Estoque origem) {
        this.origem = origem;
    }

    public Estoque getDestino() {
        return destino;
    }

    public void setDestino(Estoque destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

