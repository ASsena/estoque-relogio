package com.backend.estoquerelogios.controller;

import com.backend.estoquerelogios.dto.CreateEstoqueDTO;
import com.backend.estoquerelogios.dto.EstoqueDTO;
import com.backend.estoquerelogios.dto.EstoqueResponseDTO;
import com.backend.estoquerelogios.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("estoque")
public class EstoqueController {

    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping("{id}")
    public ResponseEntity<EstoqueDTO> getEstoque(@PathVariable Long id){
        var estoque = estoqueService.getEstoqueById(id);
        return ResponseEntity.ok(estoque);

    }

    @GetMapping("all")
    public ResponseEntity<List<EstoqueResponseDTO>> findAll() {
        List<EstoqueResponseDTO> estoques = estoqueService.listarTodos();
        return ResponseEntity.ok(estoques);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CreateEstoqueDTO createEstoqueDTO) {
        System.out.println(createEstoqueDTO.getDepositoId());
        estoqueService.adicionarEstoque(createEstoqueDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping("update")
    public ResponseEntity<EstoqueDTO> update(@RequestBody EstoqueDTO estoqueDTO) {
        System.out.println("Teste2");
        var attEstoque = estoqueService.atualizarEstoque(estoqueDTO);
        return ResponseEntity.ok(attEstoque);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        System.out.println("Teste3");
        estoqueService.removerEstoque(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete-all")
    public ResponseEntity<?> deleteAll() {
        estoqueService.removerTodosEstoques();
        return ResponseEntity.ok().build();
    }

    @GetMapping("valor-total")
    public ResponseEntity<BigDecimal> getValorTotal() {
        return ResponseEntity.ok(estoqueService.calcularValorTotalEstoque());
    }



}
