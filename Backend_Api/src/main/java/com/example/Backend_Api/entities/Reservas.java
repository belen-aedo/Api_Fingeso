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


public class Reservas {
    @Id
    private String id_reserva;
    @ManyToOne
    private Cliente cliente; //llaves foraneas
    @ManyToOne
    private Vehiculo vehiculo;//llaves foraneas
    @ManyToOne
    private  Local_arriendo local ;//llaves foraneas

    private int cantidad_dias;
    private int valor_total;
    private String estado_reserva;//Activa, Inactiva
    private String fecha_inicio_reserva;
    private String fecha_termino_reserva;
    private String fecha_reserva;
    private String hora_reserva;

}


