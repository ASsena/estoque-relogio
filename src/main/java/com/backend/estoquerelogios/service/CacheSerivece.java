package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.EstoqueDTO;
import com.backend.estoquerelogios.dto.EstoqueResponseDTO;
import com.backend.estoquerelogios.entities.Estoque;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CacheSerivece {

    private final Map<Long, EstoqueDTO> estoqueCache = new HashMap<>();
    private final Map<String, EstoqueResponseDTO> estoqueReponseCache = new HashMap<>();

    public String gerarChaveEstoque(Estoque estoque) {
        return estoque.getProduto().getId() + "-" + estoque.getDeposito().getId();
    }

    public Map<Long, EstoqueDTO> getEstoqueCache() {
        return estoqueCache;
    }

    public Map<String, EstoqueResponseDTO> getEstoqueReponseCache() {
        return estoqueReponseCache;
    }


}
