package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.CreateMovimentoDTO;
import com.backend.estoquerelogios.dto.EstoqueResponseDTO;
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

        EstoqueResponseDTO estoqueresponse = cacheSerivice.getEstoqueReponseCache().get(movimentoDTO.getEstoqueId());
        Movimento movimento = new Movimento();
        movimento.setProduto(produto);
        movimento.setTipo(movimentoDTO.getTipo());
        movimento.setDataHora(LocalDateTime.now());
        movimento.setQuantidade(movimentoDTO.getQuantidade());



        switch (movimentoDTO.getTipo()) {
            case ENTRADA -> {
                try{
                     estoqueresponse.setQuantidade(estoqueresponse.getQuantidade() + movimentoDTO.getQuantidade());
                    Estoque estoque = estoqueRepository.findById(movimentoDTO.getEstoqueId())
                            .orElseThrow(() -> new NaoExistenteException("Estoque não encontrado com ID: " + movimentoDTO.getEstoqueId()));
                    estoque.setQuantidade(estoque.getQuantidade() + movimentoDTO.getQuantidade());
                    cacheSerivice.getEstoqueReponseCache().put(cacheSerivice.gerarChaveEstoque(estoque), estoqueresponse);
                    movimento.setOrigem(estoque);
                    movimento.setDestino(estoque);
                    estoqueRepository.save(estoque);
                }catch (Exception e){
                    throw new ValorInvalidoException("É preciso preencher o estoque para saida ou entrada");
                }

            }

            case SAIDA -> {
                Estoque estoque = estoqueRepository.findById(movimentoDTO.getEstoqueId())
                        .orElseThrow(() -> new NaoExistenteException("Estoque não encontrado com ID: " + movimentoDTO.getEstoqueId()));

                if (estoque.getQuantidade() < movimentoDTO.getQuantidade()) {
                    throw new ValorInvalidoException("O valor de saída excedeu a quantidade em estoque");
                }

                estoque.setQuantidade(estoque.getQuantidade() - movimentoDTO.getQuantidade());

                movimento.setOrigem(estoque);
                movimento.setDestino(estoque);

                estoqueRepository.save(estoque);

                // Atualiza cache
                EstoqueResponseDTO estoqueResponse = cacheSerivice.getEstoqueReponseCache().get(movimentoDTO.getEstoqueId());
                if (estoqueResponse != null) {
                    estoqueResponse.setQuantidade(estoque.getQuantidade());
                    cacheSerivice.getEstoqueReponseCache().put(cacheSerivice.gerarChaveEstoque(estoque), estoqueResponse);
                }
            }


            case TRANSFERENCIA -> {
                Estoque origem = estoqueRepository.findById(movimentoDTO.getOrigemId())
                        .orElseThrow(() -> new NaoExistenteException("Estoque de origem não existe"));
                Estoque destino = estoqueRepository.findById(movimentoDTO.getDestinoId())
                        .orElseThrow(() -> new NaoExistenteException("Estoque de destino não existe"));

                if (origem.getDeposito().getId().equals(destino.getDeposito().getId())) {
                    throw new ValorInvalidoException("O depósito de origem e destino devem ser diferentes!");
                }

                if (!origem.getProduto().getId().equals(destino.getProduto().getId())) {
                    throw new ValorInvalidoException("Não é possível transferir quantidade para produtos diferentes!");
                }

                if (origem.getQuantidade() < movimentoDTO.getQuantidade()) {
                    throw new ValorInvalidoException("O valor de transferência excedeu a quantidade em estoque de origem");
                }

                origem.setQuantidade(origem.getQuantidade() - movimentoDTO.getQuantidade());
                destino.setQuantidade(destino.getQuantidade() + movimentoDTO.getQuantidade());

                movimento.setOrigem(origem);
                movimento.setDestino(destino);

                estoqueRepository.save(origem);
                estoqueRepository.save(destino);

                // Atualiza cache
                EstoqueResponseDTO origemResponse = cacheSerivice.getEstoqueReponseCache().get(origem.getId());
                if (origemResponse != null) {
                    origemResponse.setQuantidade(origem.getQuantidade());
                    cacheSerivice.getEstoqueReponseCache().put(cacheSerivice.gerarChaveEstoque(origem), origemResponse);
                }

                EstoqueResponseDTO destinoResponse = cacheSerivice.getEstoqueReponseCache().get(destino.getId());
                if (destinoResponse != null) {
                    destinoResponse.setQuantidade(destino.getQuantidade());
                    cacheSerivice.getEstoqueReponseCache().put(cacheSerivice.gerarChaveEstoque(destino), destinoResponse);
                }
            }

        } movimentoRepository.save(movimento);

    }

}
