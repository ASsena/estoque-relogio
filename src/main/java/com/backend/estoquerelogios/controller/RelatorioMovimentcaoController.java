package com.backend.estoquerelogios.controller;

import com.backend.estoquerelogios.repository.MovimentoRepository;
import com.backend.estoquerelogios.service.RelatorioMovimentacaoService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("relatorio")
public class RelatorioMovimentcaoController {

    @Autowired
    private RelatorioMovimentacaoService relatorioMovimentacaoService;
    @Autowired
    private MovimentoRepository movimentoRepository;

    @GetMapping
    public ResponseEntity<String> gerarRelatorioMovimentacao(@RequestParam String caminho) throws JRException {
        System.out.println(caminho);
        relatorioMovimentacaoService.gerarRelatorioMovimentacao(caminho);

        return ResponseEntity.ok("Relatório criado com sucesso" + caminho);
    }
}
