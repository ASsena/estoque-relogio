package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.RelatorioMovimentoDTO;
import com.backend.estoquerelogios.entities.Movimento;
import com.backend.estoquerelogios.repository.MovimentoRepository;
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

    public void gerarRelatorioMovimentacao(String caminho) throws JRException{

        List<Movimento> movimentacoes = movimentoRepository.findAll();

        List<RelatorioMovimentoDTO> dados = movimentacoes.stream().map(RelatorioMovimentoDTO::new).collect(Collectors.toList());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
        System.out.println(dados.size());

        Map<String, Object> parametros = new HashMap<>();

        parametros.put("título", "Relatório de Movimentações");

        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/relatorios/relatorio_movimentacao.jrxml")
        );

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminho);


    }
}
