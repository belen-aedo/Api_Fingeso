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
@Table(name = "cliente")
@Entity
public class Cliente {

    @Id
    private String rut_cliente;//DNI del cliente
    private String nombre_cliente;//Nombre del cliente
    private String apellido_cliente;//Apellido del cliente
    private String direccion_cliente;//Dirección del cliente
    private String telefono_cliente;//Teléfono del cliente
    private String correo_cliente;//Correo electrónico del cliente
    private LocalDate fecha_nacimiento_cliente;//Fecha de nacimiento del cliente
}