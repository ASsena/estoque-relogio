package com.backend.estoquerelogios.controller;

import com.backend.estoquerelogios.dto.CreateProdutoDTO;
import com.backend.estoquerelogios.dto.ProdutoDTO;
import com.backend.estoquerelogios.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestBody @Valid CreateProdutoDTO produto) {
        produtoService.adicionarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@RequestBody ProdutoDTO produto, @PathVariable Long id) {
        produtoService.updateProduto(produto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<Object> delete(@PathVariable String codigo) {
        produtoService.removerProduto(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("{codigo}")
    public ResponseEntity<Object> getByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(produtoService.produtoFindByCodigo(codigo));
    }

}
