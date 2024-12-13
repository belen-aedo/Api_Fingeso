package com.example.Backend_Api.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class VehiculoReferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_publicacion;//Identificador

    private String descripcion_publicacion;

    private LocalDate fecha_publicacion;

    private boolean estado_publicacion;//disponibilida

    private double precio_publicacion;

    public long getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(long id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getDescripcion_publicacion() {
        return descripcion_publicacion;
    }

    public void setDescripcion_publicacion(String descripcion_publicacion) {
        this.descripcion_publicacion = descripcion_publicacion;
    }

    public LocalDate getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDate fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public boolean isEstado_publicacion() {
        return estado_publicacion;
    }

    public void setEstado_publicacion(boolean estado_publicacion) {
        this.estado_publicacion = estado_publicacion;
    }

    public double getPrecio_publicacion() {
        return precio_publicacion;
    }

    public void setPrecio_publicacion(double precio_publicacion) {
        this.precio_publicacion = precio_publicacion;
    }
}
