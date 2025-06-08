package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.RelatorioMovimentoDTO;
import com.backend.estoquerelogios.entities.Movimento;
import com.backend.estoquerelogios.repository.MovimentoRepository;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioMovimentacaoService {

    @Autowired
    MovimentoRepository movimentoRepository;

    public void gerarRelatorioMovimentacao(HttpServletResponse response) throws Exception{

        List<Movimento> movimentacoes = movimentoRepository.findAll();

        List<RelatorioMovimentoDTO> dados = movimentacoes.stream().map(RelatorioMovimentoDTO::new).collect(Collectors.toList());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
        System.out.println(dados.size());

        Map<String, Object> parametros = new HashMap<>();

        parametros.put("título", "Relatório de Movimentações");

        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/relatorios/relatorio_movimentacao.jrxml")
        );

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=relatorio.pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, response.getOutputStream().toString());


    }
}
