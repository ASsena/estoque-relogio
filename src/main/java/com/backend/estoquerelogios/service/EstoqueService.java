package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.*;
import com.backend.estoquerelogios.entities.*;
import com.backend.estoquerelogios.exception.NaoExistenteException;
import com.backend.estoquerelogios.exception.ValorInvalidoException;
import com.backend.estoquerelogios.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final DepositoRepository depositoRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentoRepository movimentoRepository;

    private final Map<Long, EstoqueDTO> estoqueCache = new HashMap<>();

    public EstoqueService(
            EstoqueRepository estoqueRepository,
            DepositoRepository depositoRepository,
            ProdutoRepository produtoRepository,
            MovimentoRepository movimentoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.depositoRepository = depositoRepository;
        this.produtoRepository = produtoRepository;
        this.movimentoRepository = movimentoRepository;
    }

    public EstoqueDTO getEstoqueById(Long id) {
        return estoqueCache.computeIfAbsent(id, this::buscarEstoqueDTOOuLancar);
    }

    public List<EstoqueResponseDTO> listarTodos() {
        return estoqueRepository.findAll().stream()
                .map(this::converterParaEstoqueResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void adicionarEstoque(CreateEstoqueDTO dto) {
        validarEstoqueDTO(dto);

        Produto produto = buscarProdutoOuLancar(dto.getProdutoId());
        Deposito deposito = buscarDepositoOuLancar(dto.getDepositoId());

        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setDeposito(deposito);
        estoque.setQuantidade(dto.getQuantidade());

        estoqueRepository.save(estoque);
    }

    public BigDecimal calcularValorTotalEstoque(Long idDeposito) {
        var deposito = depositoRepository.findById(idDeposito);
        if(deposito.isEmpty()){
            throw new NaoExistenteException("Depósito não existe");
        }
        return estoqueRepository.calcularValorTotalPorDeposito(deposito.get());
    }

    @Transactional
    public EstoqueDTO atualizarEstoque(EstoqueDTO dto) {
        if (dto.getId() == null) return null;

        Estoque estoque = buscarEstoqueOuLancar(dto.getId());

        if (dto.getProdutoId() != null) {
            Produto produto = buscarProdutoOuLancar(dto.getProdutoId());
            estoque.setProduto(produto);
        }

        if (dto.getDepositoId() != null) {
            Deposito deposito = buscarDepositoOuLancar(dto.getDepositoId());
            estoque.setDeposito(deposito);
        }

        if (dto.getQuantidade() != null) {
            if (dto.getQuantidade() < 0) {
                throw new ValorInvalidoException("Quantidade deve ser maior que 0");
            }
            estoque.setQuantidade(dto.getQuantidade());
        }

        Estoque atualizado = estoqueRepository.save(estoque);
        return new EstoqueDTO(atualizado);
    }

    @Transactional
    public void removerEstoque(Long id) {
        Estoque estoque = buscarEstoqueOuLancar(id);
        estoqueCache.remove(id);
        estoqueRepository.delete(estoque);
    }

    @Transactional
    public void removerTodosEstoques() {
        estoqueCache.clear();
        estoqueRepository.deleteAll();
    }

    // ========================
    // Métodos auxiliares
    // ========================

    private EstoqueDTO buscarEstoqueDTOOuLancar(Long id) {
        Estoque estoque = buscarEstoqueOuLancar(id);
        return new EstoqueDTO(estoque);
    }

    private Estoque buscarEstoqueOuLancar(Long id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new NaoExistenteException("Estoque não encontrado com ID: " + id));
    }

    private Produto buscarProdutoOuLancar(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NaoExistenteException("Produto não encontrado com ID: " + id));
    }

    private Deposito buscarDepositoOuLancar(Long id) {
        return depositoRepository.findById(id)
                .orElseThrow(() -> new NaoExistenteException("Depósito não encontrado com ID: " + id));
    }

    private void validarEstoqueDTO(CreateEstoqueDTO dto) {
        if (dto.getQuantidade() == null || dto.getQuantidade() < 0) {
            throw new ValorInvalidoException("Quantidade deve ser maior que 0");
        }
    }

    private EstoqueResponseDTO converterParaEstoqueResponseDTO(Estoque estoque) {
        ProdutoDTO produto = new ProdutoDTO(buscarProdutoOuLancar(estoque.getProduto().getId()));
        DepositoDTO deposito = new DepositoDTO(buscarDepositoOuLancar(estoque.getDeposito().getId()));
        return new EstoqueResponseDTO(deposito, produto, estoque.getQuantidade());
    }
}

