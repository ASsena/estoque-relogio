package com.backend.estoquerelogios.repository;

import com.backend.estoquerelogios.entities.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {

    Optional<Deposito> findByNome(String nome);
}
