package com.example.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "vehiculoReferencia")
@Entity

public class VehiculoReferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPublicacion;//Identificador
    private String descripcionPublicacion;
    private LocalDate fechaPublicacion;
    private boolean estadoPublicacion;//disponibilidad
    private double precioPublicacion;

    //precio, capacidad de pasajeros, cantidad de puertas, tipo de gasolina, mecánico o automático,
    public VehiculoReferencia(long idPublicacion, String descripcionPublicacion, LocalDate fechaPublicacion, boolean estadoPublicacion, double precioPublicacion) {
        this.idPublicacion = idPublicacion;
        this.descripcionPublicacion = descripcionPublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.estadoPublicacion = estadoPublicacion;
        this.precioPublicacion = precioPublicacion;
    }

    public VehiculoReferencia() {

    }
}
