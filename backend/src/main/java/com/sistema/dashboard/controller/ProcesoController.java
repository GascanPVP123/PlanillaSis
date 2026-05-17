package com.sistema.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.dashboard.model.ProcesoPeriodo;
import com.sistema.dashboard.repository.ProcesoRepository;

@RestController
@RequestMapping("/api/procesos")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*") 
public class ProcesoController {

    private final ProcesoRepository procesoRepository;

    public ProcesoController(ProcesoRepository procesoRepository) {
        this.procesoRepository = procesoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProcesoPeriodo>> listarProcesos() {
        try {
            List<ProcesoPeriodo> procesos = procesoRepository.findAll();
            return ResponseEntity.ok(procesos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearProceso(@RequestBody Map<String, Object> datosProceso) {
        try {
            ProcesoPeriodo nuevo = new ProcesoPeriodo();
            

            nuevo.setMes((String) datosProceso.get("mes"));
            nuevo.setAnio((Integer) datosProceso.get("anio"));
            nuevo.setEmpleados((Integer) datosProceso.get("empleados"));
            nuevo.setHorasExtras((Integer) datosProceso.get("horasExtras"));
            
            String estadoStr = (String) datosProceso.get("estado"); // Recibe "EN_PROCESO"
            nuevo.setEstado(com.sistema.dashboard.model.EstadoProceso.valueOf(estadoStr));

            String gastosStr = datosProceso.get("gastos").toString();
            nuevo.setGastos(new java.math.BigDecimal(gastosStr));

            procesoRepository.save(nuevo); 

            return ResponseEntity.ok().body(Map.of("status", "success"));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar en la base de datos: " + e.getMessage());
        }
    }
}