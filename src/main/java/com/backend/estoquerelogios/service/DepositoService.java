package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.CreateDepositoDTO;
import com.backend.estoquerelogios.dto.DepositoDTO;
import com.backend.estoquerelogios.entities.Deposito;
import com.backend.estoquerelogios.exception.JaExistenteException;
import com.backend.estoquerelogios.exception.NaoExistenteException;
import com.backend.estoquerelogios.repository.DepositoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepositoService {

    private final DepositoRepository depositoRepository;
    private Map<Long, DepositoDTO> depositosCache = new HashMap<>();

    public DepositoService(DepositoRepository depositoRepository) {
        this.depositoRepository = depositoRepository;
    }

    public List<DepositoDTO> findAll() {
        List<Deposito> depositos = depositoRepository.findAll();
        List<DepositoDTO> depositoDTOS = new ArrayList<>();
        for (Deposito deposito : depositos) {
            var depositoDTO = new DepositoDTO(deposito);
            depositoDTOS.add(depositoDTO);
        }
        return depositoDTOS;
    }

    public DepositoDTO procurarPorId(Long id) {
        return depositosCache.computeIfAbsent(id, idKey ->
                depositoRepository.findById(idKey)
                        .map(DepositoDTO::new)
                        .orElseThrow(() -> new NaoExistenteException("Depósito com o ID " + idKey + " não existe"))
        );
    }


    @Transactional
    public void adicionarDeposito(CreateDepositoDTO depositoDTO) {
        Deposito deposito = new Deposito();
        var depositoBanco = depositoRepository.findByNome(depositoDTO.getNome());
        if(depositoBanco.isPresent()){
            throw new JaExistenteException(
                    "Depósito " + depositoDTO.getNome() + " já cadastrado" );
        }
        deposito.setNome(depositoDTO.getNome());
        deposito.setLocalizacao(depositoDTO.getLocalizacao());
        depositoRepository.save(deposito);
    }

    @Transactional
    public DepositoDTO updateDeposito(DepositoDTO depositoDTO) {
        Deposito deposito = depositoRepository.findById(depositoDTO.getId())
                .orElseThrow( () -> new NaoExistenteException("Deposito não encontrado com ID: " + depositoDTO.getId()));

        if (depositoDTO.getNome() != null ) {
            deposito.setNome(depositoDTO.getNome());
        }
        if(depositoDTO.getLocalizacao() != null){
            deposito.setLocalizacao(depositoDTO.getLocalizacao());
        }
        depositoRepository.save(deposito);
        return new DepositoDTO(deposito);
    }

    @Transactional
    public void removerDeposito(Long id) {
        var deposito = depositoRepository.findById(id).orElseThrow(
                () -> new NaoExistenteException(
                        "Não é possível remover depósito com ID: " + id  + " pois ele não existe"));
        depositosCache.remove(deposito.getId());
        depositoRepository.delete(deposito);
    }
}
