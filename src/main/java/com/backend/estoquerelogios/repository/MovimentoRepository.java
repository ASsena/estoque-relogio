package com.backend.estoquerelogios.repository;

import com.backend.estoquerelogios.entities.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
}
