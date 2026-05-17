package com.sistema.dashboard.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.dashboard.model.ProcesoPeriodo;
import com.sistema.dashboard.repository.ProcesoRepository;
import com.sistema.dashboard.service.ProcesoService;

@Service 
public class ProcesoServiceImpl implements ProcesoService {

    private final ProcesoRepository procesoRepository;

    public ProcesoServiceImpl(ProcesoRepository procesoRepository) {
        this.procesoRepository = procesoRepository;
    }

    @Override
    public List<ProcesoPeriodo> obtenerTodosLosProcesos() {
        return procesoRepository.findAll();
    }
}