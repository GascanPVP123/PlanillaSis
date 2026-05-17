package com.sistema.dashboard.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proceso_periodos")
@Data 
@NoArgsConstructor 
public class ProcesoPeriodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String mes;

    @Column(nullable = false)
    private Integer anio;

    @Enumerated(EnumType.STRING) 
    @Column(nullable = false, length = 20)
    private EstadoProceso estado;

    @Column(nullable = false)
    private Integer empleados;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal gastos;

    @Column(name = "horas_extras", nullable = false)
    private Integer horasExtras;
}