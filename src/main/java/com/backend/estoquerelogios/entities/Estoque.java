package com.backend.estoquerelogios.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Deposito deposito;

    @Column(nullable = false)
    private Integer quantidade;

    public Estoque(Long id, Produto produto, Deposito deposito, Integer quantidade) {
        this.id = id;
        this.produto = produto;
        this.deposito = deposito;
        this.quantidade = quantidade;
    }

    public Estoque() {
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

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
