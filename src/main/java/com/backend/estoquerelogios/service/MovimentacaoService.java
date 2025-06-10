package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.CreateMovimentoDTO;
import com.backend.estoquerelogios.dto.EstoqueResponseDTO;
import com.backend.estoquerelogios.dto.MovimentoDTO;
import com.backend.estoquerelogios.entities.Deposito;
import com.backend.estoquerelogios.entities.Estoque;
import com.backend.estoquerelogios.entities.Movimento;
import com.backend.estoquerelogios.entities.Produto;
import com.backend.estoquerelogios.exception.NaoExistenteException;
import com.backend.estoquerelogios.exception.ValorInvalidoException;
import com.backend.estoquerelogios.repository.DepositoRepository;
import com.backend.estoquerelogios.repository.EstoqueRepository;
import com.backend.estoquerelogios.repository.MovimentoRepository;
import com.backend.estoquerelogios.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentoRepository movimentoRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private DepositoRepository depositoRepository;
    @Autowired
    private CacheSerivece cacheSerivice;


    @Transactional
    public void registrarMovimentacao(CreateMovimentoDTO movimentoDTO) {
        Produto produto = produtoRepository.findById(movimentoDTO.getProdutoId())
                .orElseThrow(() -> new NaoExistenteException("Produto não encontrado com ID: " + movimentoDTO.getProdutoId()));
        System.out.println("RESOLVENDO ERRO!!!");
        System.out.println(produto.getNome());
        System.out.println(movimentoDTO.getProdutoId());
        System.out.println(movimentoDTO.getTipo().name());

        System.out.println(movimentoDTO.getEstoqueId());

        Movimento movimento = new Movimento();
        movimento.setProduto(produto);
        movimento.setTipo(movimentoDTO.getTipo());
        movimento.setDataHora(LocalDateTime.now());
        movimento.setQuantidade(movimentoDTO.getQuantidade());



        switch (movimentoDTO.getTipo()) {
            case ENTRADA -> {
                try{
                    Deposito deposito = depositoRepository.findById(movimentoDTO.getEstoqueId()).orElseThrow(
                            () -> new NaoExistenteException("Depósito não encontrado"));

                    List<Estoque> estoque = estoqueRepository.findByDeposito(deposito);
                    for(Estoque estoque1 : estoque){
                        if(estoque1.getProduto().equals(produto)){
                            String chave = cacheSerivice.gerarChaveEstoque(estoque1);
                            EstoqueResponseDTO estoqueresponse = cacheSerivice.getEstoqueReponseCache().get(chave);
                            estoqueresponse.setQuantidade(estoque1.getQuantidade() + movimentoDTO.getQuantidade());
                            cacheSerivice.getEstoqueReponseCache().put(chave, estoqueresponse);
                            estoque1.setQuantidade(estoque1.getQuantidade() + movimentoDTO.getQuantidade());
                            System.out.println(estoque1.getProduto().getNome());

                            movimento.setOrigem(estoque1);
                            movimento.setDestino(estoque1);
                            estoqueRepository.saveAndFlush(estoque1);
                        }
                    }

                }catch (Exception e){
                    throw new ValorInvalidoException(e.getMessage());
                }

            }

            case SAIDA -> {
                Deposito deposito = depositoRepository.findById(movimentoDTO.getEstoqueId()).orElseThrow(
                        () -> new NaoExistenteException("Depósito não encontrado"));
                List<Estoque> estoque = estoqueRepository.findByDeposito(deposito);
                for(Estoque estoque1 : estoque){
                    if(estoque1.getProduto().equals(produto)){
                        String chave = cacheSerivice.gerarChaveEstoque(estoque1);
                        EstoqueResponseDTO estoqueresponse = cacheSerivice.getEstoqueReponseCache().get(chave);
                        if(estoque1.getQuantidade() < movimentoDTO.getQuantidade()){
                            throw new ValorInvalidoException("Quantidade de movimentação maior do que a quantidade em estoque");
                        }

                        estoqueresponse.setQuantidade(estoque1.getQuantidade() - movimentoDTO.getQuantidade());
                        cacheSerivice.getEstoqueReponseCache().put(chave, estoqueresponse);
                        estoque1.setQuantidade(estoque1.getQuantidade() + movimentoDTO.getQuantidade());
                        System.out.println(estoque1.getProduto().getNome());

                        movimento.setOrigem(estoque1);
                        movimento.setDestino(estoque1);
                        estoqueRepository.saveAndFlush(estoque1);
                    }
                }
            }


            case TRANSFERENCIA -> {
                Deposito depositoOrigem = depositoRepository.findById(movimentoDTO.getOrigemId()).orElseThrow(
                        () -> new NaoExistenteException("Depósito não encontrado"));
                Deposito depositoDestino = depositoRepository.findById(movimentoDTO.getDestinoId()).orElseThrow(
                        () -> new NaoExistenteException("Depósito não encontrado"));

                List<Estoque> origem = estoqueRepository.findByDeposito(depositoOrigem);
                List<Estoque> destino = estoqueRepository.findByDeposito(depositoDestino);

                Estoque estoqueOrigem = origem.stream()
                        .filter(e -> e.getProduto().equals(produto) && e.getQuantidade() >= movimentoDTO.getQuantidade())
                        .findFirst()
                        .orElseThrow(() -> new NaoExistenteException("Produto não encontrado ou quantidade insuficiente"));

                estoqueOrigem.setQuantidade(estoqueOrigem.getQuantidade() - movimentoDTO.getQuantidade());

                Estoque estoqueDestino = destino.stream()
                        .filter(e -> e.getProduto().equals(produto))
                        .findFirst()
                        .orElse(null);

                if (estoqueDestino == null) {
                    estoqueDestino = new Estoque();
                    estoqueDestino.setProduto(produto);
                    estoqueDestino.setDeposito(depositoDestino);
                    estoqueDestino.setQuantidade(0);
                }

                estoqueDestino.setQuantidade(estoqueDestino.getQuantidade() + movimentoDTO.getQuantidade());

// Atualiza cache diretamente com os objetos atualizados
                cacheSerivice.getEstoqueReponseCache().put(
                        cacheSerivice.gerarChaveEstoque(estoqueOrigem),
                        new EstoqueResponseDTO(estoqueOrigem)
                );

                cacheSerivice.getEstoqueReponseCache().put(
                        cacheSerivice.gerarChaveEstoque(estoqueDestino),
                        new EstoqueResponseDTO(estoqueDestino)
                );

// Persiste
                movimento.setOrigem(estoqueOrigem);
                movimento.setDestino(estoqueDestino);
                estoqueRepository.saveAndFlush(estoqueOrigem);
                estoqueRepository.saveAndFlush(estoqueDestino);



                movimento.setOrigem(estoqueOrigem);
                movimento.setDestino(estoqueDestino);
                estoqueRepository.saveAndFlush(estoqueOrigem);
                estoqueRepository.saveAndFlush(estoqueDestino);


            }

        } movimentoRepository.save(movimento);

    }

}
