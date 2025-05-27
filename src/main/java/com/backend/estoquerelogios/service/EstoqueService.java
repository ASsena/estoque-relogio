package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.CreateEstoqueDTO;
import com.backend.estoquerelogios.dto.EstoqueDTO;
import com.backend.estoquerelogios.entities.Estoque;
import com.backend.estoquerelogios.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private final EstoqueRepository warehouseRepository;

    public EstoqueService(EstoqueRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public EstoqueDTO create(CreateEstoqueDTO dto) {
        Estoque entity = new Estoque(dto.getNome(), dto.getLocalizacao());
        entity = warehouseRepository.save(entity);
        return toDTO(entity);
    }

    public List<WarehouseDTO> listAll() {
        return warehouseRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private WarehouseDTO toDTO(Warehouse entity) {
        return new WarehouseDTO(entity.getId(), entity.getNome(), entity.getLocalizacao());
    }
}

