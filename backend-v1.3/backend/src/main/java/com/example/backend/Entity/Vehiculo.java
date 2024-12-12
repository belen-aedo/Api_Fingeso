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
    @JoinColumn(name = "id_sucursal")
    private Sucursal ubicacionActual; // muchos vehiculos en una sucursal, un vehiculo puede estar en una sucursal, Vehiculo-M------1-Sucursal

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reserva")
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

    public long getId() {
        return id;
    }

    public String getPatente() {
        return Patente;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColorPrincipal() {
        return colorPrincipal;
    }

    public int getYear() {
        return year;
    }

    public String getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public double getKilometrajeVehiculo() {
        return kilometrajeVehiculo;
    }

    public Sucursal getUbicacionActual() {
        return ubicacionActual;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPatente(String patente) {
        Patente = patente;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setColorPrincipal(String colorPrincipal) {
        this.colorPrincipal = colorPrincipal;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEstadoVehiculo(String estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    public void setKilometrajeVehiculo(double kilometrajeVehiculo) {
        this.kilometrajeVehiculo = kilometrajeVehiculo;
    }

    public void setUbicacionActual(Sucursal ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
