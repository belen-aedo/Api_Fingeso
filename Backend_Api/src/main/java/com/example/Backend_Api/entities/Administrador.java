package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "administrador")
@Entity

public class Administrador {
    @Id
    private String rut_administrador;
    private String nombre_administrador;
    private String apellido_administrador;
    private String direccion_administrador;
    private String telefono_administrador;
    private String correo_administrador;
    private LocalDate fecha_nacimiento_administrador;
    private String contrasena_administrador;
    private String estado_administrador;//Activo, Inactivo
    private String lugar_de_trabajo_administrador;


}

