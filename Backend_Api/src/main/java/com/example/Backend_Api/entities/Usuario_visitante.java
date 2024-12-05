package com.example.Backend_Api.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Usuario_visitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_visitante;//Identificador
}
