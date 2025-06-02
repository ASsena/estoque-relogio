package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.CreateEstoqueDTO;
import com.backend.estoquerelogios.dto.DepositoDTO;
import com.backend.estoquerelogios.dto.EstoqueDTO;
import com.backend.estoquerelogios.dto.ProdutoDTO;
import com.backend.estoquerelogios.entities.Deposito;
import com.backend.estoquerelogios.entities.Estoque;
import com.backend.estoquerelogios.entities.Produto;
import com.backend.estoquerelogios.exception.NaoExistenteException;
import com.backend.estoquerelogios.exception.ProdutoNaoExistente;
import com.backend.estoquerelogios.exception.ValorInvalidoException;
import com.backend.estoquerelogios.repository.DepositoRepository;
import com.backend.estoquerelogios.repository.EstoqueRepository;
import com.backend.estoquerelogios.repository.ProdutoRepository;
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
    private Map<Long, EstoqueDTO> estoqueCache = new HashMap<>();

    public EstoqueService(EstoqueRepository estoqueRepository, DepositoRepository depositoRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.depositoRepository = depositoRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<EstoqueDTO> findAll() {
        var estoques = estoqueRepository.findAll();
        List<ProdutoDTO> produtos = estoques.stream()
                .map(p -> produtoRepository.findById(p.getProduto().getId())
                        .orElseThrow(() -> new ProdutoNaoExistente("Produto com ID " + p.getProduto().getId() + " não encontrado")))
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());
        List<DepositoDTO> depositos = estoques.stream()
                .map(d -> depositoRepository.findById(d.getDeposito().getId()))
                        .orElseThrow(() -> new ProdutoNaoExistente("Produto com ID " + p.getProduto().getId() + " não encontrado")))
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());


    }

    @Transactional
    public void adicionarEstoque(CreateEstoqueDTO estoqueDTO) {
        Estoque estoque = new Estoque();
        var produtoEstoque = produtoRepository.findById(estoqueDTO.getProdutoId());
        var depositoEstoque = depositoRepository.findById(estoqueDTO.getDepositoId());
        if(depositoEstoque.isEmpty()) {
            throw new NaoExistenteException("Depósito não encontrado");
        }
        if(produtoEstoque.isEmpty()) {
            throw new NaoExistenteException("Produto não encontrado");
        }
        if(estoqueDTO.getQuantidade() == null || estoqueDTO.getQuantidade() < 0) {
            throw new ValorInvalidoException("Quantidade deve ser maior que 0");
        }
        estoque.setDeposito(depositoEstoque.get());
        estoque.setProduto(produtoEstoque.get());
        estoque.setQuantidade(estoqueDTO.getQuantidade());
        estoqueRepository.save(estoque);
    }

    public BigDecimal ValorTotalEstoque(){
        List<Estoque> estoque = estoqueRepository.findAll();
        List<Produto> produtos = produtoRepository.findAll();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(Produto produto : produtos){
            for (Estoque estoqueP : estoque){
                if(estoqueP.getProduto().getId().equals(produto.getId())){
                    valorTotal.add(produto.getPrecoUnitario().multiply(BigDecimal.valueOf(estoqueP.getQuantidade())));
                }
            }
        }
        return valorTotal;
    }

    @Transactional
    public DepositoDTO updateDeposito(DepositoDTO depositoDTO) {
        Deposito deposito = depositoRepository.findById(depositoDTO.getId())
                .orElseThrow( () -> new NaoExistenteException("Deposito não encontrado com ID: " + depositoDTO.getId()));

        if (depositoDTO.getNome() != null ) {
            deposito.setNome(depositoDTO.getNome());
        }
        if(depositoDTO.getLocalizacao() != null){
            deposito.setLocalizacao(depositoDTO.getLocalizacao());
        }
        depositoRepository.save(deposito);
        return new DepositoDTO(deposito);
    }

    @Transactional
    public void removerEstoque(Long id) {
        var deposito = depositoRepository.findById(id).orElseThrow(
                () -> new NaoExistenteException(
                        "Não é possível remover depósito com ID: " + id  + " pois ele não existe"));
        depositosCache.remove(deposito.getId());
        depositoRepository.delete(deposito);
    }



}
