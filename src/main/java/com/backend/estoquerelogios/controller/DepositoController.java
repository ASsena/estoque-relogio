package com.backend.estoquerelogios.controller;

import com.backend.estoquerelogios.dto.CreateDepositoDTO;
import com.backend.estoquerelogios.dto.DepositoDTO;
import com.backend.estoquerelogios.service.DepositoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deposito")
public class DepositoController {

    private DepositoService depositoService;

    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestBody @Valid CreateDepositoDTO depositorioDTO) {
        depositoService.adicionarDeposito(depositorioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody DepositoDTO deposito) {
        depositoService.updateDeposito(deposito);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        depositoService.removerDeposito(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(depositoService.findAll());
    }

    @GetMapping("serch-codigo/{id}")
    public ResponseEntity<Object> getByCodigo(@PathVariable Long id) {
        return ResponseEntity.ok(depositoService.procurarPorId(id));
    }
}
