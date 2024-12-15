package com.example.backend.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "vehiculo")
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String patente; // Placa del vehículo
    private String marca; // Marca del vehículo
    private String modelo; // Modelo del vehículo
    private String colorPrincipal; // Color del vehículo
    private int year; // Año de fabricación
    private String estadoVehiculo; // D: Disponible, O: Ocupado, M: Mantenimiento
    private double kilometrajeVehiculo; // Kilometraje del vehículo

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal ubicacionActual; // muchos vehiculos en una sucursal, un vehiculo puede estar en una sucursal, Vehiculo-M------1-Sucursal

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reserva",  nullable = true)
    private Reserva reserva; // una vehiculo-1----1-reserva

    public Vehiculo(long id, String patente, String marca, String modelo, String colorPrincipal, int year, Character estadoVehiculo, double kilometrajeVehiculo, Sucursal ubicacionActual, Reserva reserva) {
        this.id = id;
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.colorPrincipal = colorPrincipal;
        this.year = year;
        this.estadoVehiculo = estadoVehiculo;
        this.kilometrajeVehiculo = kilometrajeVehiculo;
        this.ubicacionActual = ubicacionActual;
        this.reserva = reserva;
    }

    public Vehiculo() {}

    public long getId() {
        return id;
    }

    public String getPatente() {
        return patente;
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
        this.patente = patente;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return id == vehiculo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
