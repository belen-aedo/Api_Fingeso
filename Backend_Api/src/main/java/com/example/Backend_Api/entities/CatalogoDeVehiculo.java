package com.example.Backend_Api.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class CatalogoDeVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_catalogo;//Identificador
    private List<Publicacion> listado_vehiculos;
    private String descripcion_catalogo;

    public long getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(long id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public List<Publicacion> getListado_vehiculos() {
        return listado_vehiculos;
    }

    public void setListado_vehiculos(List<Publicacion> listado_vehiculos) {
        this.listado_vehiculos = listado_vehiculos;
    }

    public String getDescripcion_catalogo() {
        return descripcion_catalogo;
    }

    public void setDescripcion_catalogo(String descripcion_catalogo) {
        this.descripcion_catalogo = descripcion_catalogo;
    }
}

