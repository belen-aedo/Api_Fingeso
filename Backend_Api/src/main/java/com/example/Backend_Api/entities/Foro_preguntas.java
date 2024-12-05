package com.example.Backend_Api.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.List;

public class Foro_preguntas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_pregunta_foro;//Identificador
    private String pregunta_foro;
    @ManyToOne
    private Usuario_registrado cliente;//llaves foraneas
    private LocalDate fecha_creacion_pregunta;
    private List<String> listado_respuestas;

}
