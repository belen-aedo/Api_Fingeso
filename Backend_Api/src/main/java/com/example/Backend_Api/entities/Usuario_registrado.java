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
@Table(name = "usuario_registrado")
@Entity
public class Usuario_registrado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_usu_registrado;//Identificador
    private String rut_registrado;//DNI del cliente
    private String nombre_registrado;//Nombre del cliente
    private String apellido_registrado;//Apellido del cliente
    private String direccion_registrado;//Dirección del cliente
    private String telefono_registrado;//Teléfono del cliente
    private String correo_registrado;//Correo electrónico del cliente
    private LocalDate fecha_nacimiento_cliente;//Fecha de nacimiento del cliente


}
