package com.example.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "vehiculo_Referencia")
@Entity

public class VehiculoReferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVehiculoReferencia;//Identificador
    private String descripcionPublicacion;
    private LocalDate fechaPublicacion;
    private boolean estadoPublicacion;//disponibilidad
    private double precioPublicacion;

    //precio, capacidad de pasajeros, cantidad de puertas, tipo de gasolina, mecánico o automático,
    public VehiculoReferencia(long idVehiculoReferencia, String descripcionPublicacion, LocalDate fechaPublicacion, boolean estadoPublicacion, double precioPublicacion) {
        this.idVehiculoReferencia = idVehiculoReferencia;
        this.descripcionPublicacion = descripcionPublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.estadoPublicacion = estadoPublicacion;
        this.precioPublicacion = precioPublicacion;
    }

    public VehiculoReferencia() {

    }

    public long getIdVehiculoReferencia() {
        return idVehiculoReferencia;
    }

    public String getDescripcionPublicacion() {
        return descripcionPublicacion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public boolean isEstadoPublicacion() {
        return estadoPublicacion;
    }

    public double getPrecioPublicacion() {
        return precioPublicacion;
    }

    public void setIdVehiculoReferencia(long idPublicacion) {
        this.idVehiculoReferencia = idPublicacion;
    }

    public void setDescripcionPublicacion(String descripcionPublicacion) {
        this.descripcionPublicacion = descripcionPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setEstadoPublicacion(boolean estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public void setPrecioPublicacion(double precioPublicacion) {
        this.precioPublicacion = precioPublicacion;
    }
}
