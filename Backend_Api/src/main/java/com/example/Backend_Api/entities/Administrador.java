package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "administrador")
@Entity

public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_admin;//Identificador
    private String rut_administrador;
    private String nombre_administrador;
    private String apellido_administrador;
    private String direccion_administrador;
    private String telefono_administrador;
    private String correo_administrador;
    private String contrasena_administrador;
    private String lugar_de_trabajo_administrador;//local

}

