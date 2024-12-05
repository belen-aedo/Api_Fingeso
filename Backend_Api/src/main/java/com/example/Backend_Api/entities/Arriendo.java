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
@Table(name = "arriendo")
@Entity
public class Arriendo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_arriendo;//Identificador
    @ManyToOne
    private Usuario_registrado cliente; //llaves foraneas
    @ManyToOne
    private Vehiculo vehiculo;//llaves foraneas
    @ManyToOne
    private Sucursal local ;//llaves foraneas
    private double monto_total;
    private LocalDate fecha_inicio_arriendo;
    private LocalDate fecha_termino_arriendo;
    private String estado_arriendo;//Activa, Inactiva
}
