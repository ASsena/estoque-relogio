package com.backend.estoquerelogios.controller;

import com.backend.estoquerelogios.dto.CreateMovimentoDTO;
import com.backend.estoquerelogios.service.MovimentacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movimentacao")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;
    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping("cadastrar")
    public ResponseEntity cadastrarMovimentacao(@RequestBody CreateMovimentoDTO createMovimentoDTO){
        movimentacaoService.registrarMovimentacao(createMovimentoDTO);
        System.out.println(createMovimentoDTO.getEstoqueId());
        System.out.println(createMovimentoDTO.getQuantidade());
        System.out.println(createMovimentoDTO.getProdutoId());
        System.out.println(createMovimentoDTO.getTipo().name());
        return ResponseEntity.ok().build();
    }



}
