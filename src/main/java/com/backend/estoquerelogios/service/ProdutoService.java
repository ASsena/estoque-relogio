package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.CreateProdutoDTO;
import com.backend.estoquerelogios.dto.ProdutoDTO;
import com.backend.estoquerelogios.entities.Produto;
import com.backend.estoquerelogios.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository productRepository;

    public ProdutoService(ProdutoRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProdutoDTO createProduct(CreateProdutoDTO dto) {
        Produto product = new Produto(dto.getCodigo(), dto.getDescricao(), dto.getPrecoUnitario(), dto.getUnidadeMedida());
        product = productRepository.save(product);
        return toDTO(product);
    }

    public List<ProdutoDTO> listAll() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO getById(Long id) {
        return productRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    private ProdutoDTO toDTO(Produto product) {
        return new ProdutoDTO(
                product.getId(),
                product.getCodigo(),
                product.getDescricao(),
                product.getPrecoUnitario());
    }
}
