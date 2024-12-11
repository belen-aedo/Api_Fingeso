package com.example.backend.Entity;

import jakarta.persistence.*;

@Table(name = "vehiculo")
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String Patente; // Placa del vehículo
    private String marca; // Marca del vehículo
    private String modelo; // Modelo del vehículo
    private String colorPrincipal; // Color del vehículo
    private int year; // Año de fabricación
    private String estadoVehiculo; // Disponible, Ocupado, Mantenimiento
    private double kilometrajeVehiculo; // Kilometraje del vehículo

    @ManyToOne
    @JoinColumn(name = "sucursalId")
    private Sucursal ubicacionActual; // muchos vehiculos en una sucursal, un vehiculo puede estar en una sucursal, Vehiculo-M------1-Sucursal

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservaId")
    private Reserva reserva; // una reserva


    public Vehiculo(long id, String patente, String marca, String modelo, String colorPrincipal, int year, String estadoVehiculo, double kilometrajeVehiculo, Sucursal ubicacionActual, Reserva reserva) {
        this.id = id;
        this.Patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.colorPrincipal = colorPrincipal;
        this.year = year;
        this.estadoVehiculo = estadoVehiculo;
        this.kilometrajeVehiculo = kilometrajeVehiculo;
        this.ubicacionActual = ubicacionActual;
        this.reserva = reserva;
    }
    public Vehiculo() {

    }
}
