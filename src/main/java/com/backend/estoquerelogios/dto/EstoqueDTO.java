package com.backend.estoquerelogios.dto;

public class EstoqueDTO {

    private Long id;
    private Long produtoId;
    private String produtoDescricao;
    private Long depositoId;
    private String depositoNome;
    private Integer quantidade;

    public EstoqueDTO(Long id, Long produtoId, String produtoDescricao, Long depositoId, String depositoNome, Integer quantidade) {
        this.id = id;
        this.produtoId = produtoId;
        this.produtoDescricao = produtoDescricao;
        this.depositoId = depositoId;
        this.depositoNome = depositoNome;
        this.quantidade = quantidade;
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

    public String getDepositoNome() {
        return depositoNome;
    }

    public void setDepositoNome(String depositoNome) {
        this.depositoNome = depositoNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
