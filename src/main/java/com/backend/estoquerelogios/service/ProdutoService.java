package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.CreateProdutoDTO;
import com.backend.estoquerelogios.dto.ProdutoDTO;
import com.backend.estoquerelogios.entities.Produto;
import com.backend.estoquerelogios.exception.JaExistenteException;
import com.backend.estoquerelogios.exception.NaoExistenteException;
import com.backend.estoquerelogios.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private Map<String, ProdutoDTO> produtoCache = new HashMap();

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        for (Produto produto : produtos) {
            var produtoDTO = new ProdutoDTO(produto);
            produtoDTOs.add(produtoDTO);
        }
        return produtoDTOs;
    }

    public ProdutoDTO produtoFindByCodigo(String codigo) {
        return produtoCache.computeIfAbsent(codigo, produto ->
                produtoRepository.findByCodigo(produto)
                        .map(ProdutoDTO::new)
                        .orElseThrow(() -> new NaoExistenteException("Produto com o código " + codigo + " não existe"))
                );
    }

    @Transactional
    public void adicionarProduto(CreateProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        var produtoBanco = produtoRepository.findByCodigo(produtoDTO.getCodigo());
        if(produtoBanco.isPresent()){
            throw new JaExistenteException(
                    "Produto com o código " + produtoDTO.getCodigo() + " já existe");
        }
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        produto.setCodigo(produtoDTO.getCodigo());
        produto.setNome(produtoDTO.getNome());
        produtoRepository.save(produto);
    }

    @Transactional
    public ProdutoDTO updateProduto(ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(produtoDTO.getId())
                .orElseThrow( () -> new NaoExistenteException("Produto não encontrado com ID: " + produtoDTO.getId()));

        if (produtoDTO.getDescricao() != null ) {
            produto.setDescricao(produtoDTO.getDescricao());
        }
        if (produtoDTO.getPrecoUnitario() != null) {
            produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        }
        if (produtoDTO.getCodigo() != null) {
            produto.setCodigo(produtoDTO.getCodigo());
        }
        if (produtoDTO.getNome() != null) {
            produto.setNome(produtoDTO.getNome());
        }
        Produto atualizado = produtoRepository.save(produto);
        return new ProdutoDTO(atualizado);
    }

    @Transactional
    public void removerProduto(Long id) {
        var produto = produtoRepository.findById(id).orElseThrow(
                () -> new NaoExistenteException(
                        "Não é possível remover produto com ID: " + id  + " pois ele não existe"));
                produtoCache.remove(produto.getCodigo());
                produtoRepository.delete(produto);
    }



}



