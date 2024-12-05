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
@Table(name = "reservas")
@Entity


public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_reserva;//Identificador del vehículo
    @ManyToOne
    private Usuario_registrado usuarioRegistrado; //llaves foraneas
    @ManyToOne
    private Vehiculo vehiculo;//llaves foraneas
    @ManyToOne
    private Sucursal local ;//llaves foraneas

    private int cantidad_dias;
    private int valor_total;
    private boolean estado_reserva;//Activa, Inactiva
    private String fecha_inicio_reserva;
    private String fecha_termino_reserva;
    private String fecha_reserva;
    private String hora_reserva;

}


