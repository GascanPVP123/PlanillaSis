package com.sistema.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.dashboard.model.ProcesoPeriodo;

@Repository
public interface ProcesoRepository extends JpaRepository<ProcesoPeriodo, Long> {
}