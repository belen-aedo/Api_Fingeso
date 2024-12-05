package com.example.Backend_Api.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_publicacion;//Identificador
    private String descripcion_publicacion;
    private LocalDate fecha_publicacion;
    private boolean estado_publicacion;//disponibilida
    private double precio_publicacion;

}
