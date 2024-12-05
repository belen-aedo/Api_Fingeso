package com.example.Backend_Api.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class Catalogo_de_vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_catalogo;//Identificador
    private List<Publicacion> listado_vehiculos;
    private String descripcion_catalogo;
}

