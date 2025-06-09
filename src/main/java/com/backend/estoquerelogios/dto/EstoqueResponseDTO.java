package com.backend.estoquerelogios.dto;

public class EstoqueResponseDTO{

        private DepositoDTO depositoDTO;
        private ProdutoDTO produtoDTO;
        private int quantidade;

    public EstoqueResponseDTO(DepositoDTO depositoDTO, ProdutoDTO produtoDTO, int quantidade) {
        this.depositoDTO = depositoDTO;
        this.produtoDTO = produtoDTO;
        this.quantidade = quantidade;
    }

    public EstoqueResponseDTO() {
    }

    public DepositoDTO getDepositoDTO() {
        return depositoDTO;
    }

    public void setDepositoDTO(DepositoDTO depositoDTO) {
        this.depositoDTO = depositoDTO;
    }

    public ProdutoDTO getProdutoDTO() {
        return produtoDTO;
    }

    public void setProdutoDTO(ProdutoDTO produtoDTO) {
        this.produtoDTO = produtoDTO;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
