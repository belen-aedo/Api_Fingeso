package com.example.Backend_Api.entities;

import jakarta.persistence.*;

@Table(name = "vehiculo")
@Entity
public class Vehiculo {
    @Id
    private String placa_vehiculo; // Placa del vehículo
    private String marca_vehiculo; // Marca del vehículo
    private String modelo_vehiculo; // Modelo del vehículo
    private String color_vehiculo; // Color del vehículo
    private int anio_vehiculo; // Año de fabricación
    private String estado_vehiculo; // Disponible, Ocupado, Mantenimiento
    private double kilometraje_vehiculo; // Kilometraje del vehículo

    @ManyToOne
    private Sucursal sucursal_origen;

    @OneToOne
    private Reserva reserva;

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public Reserva getReserva_vehiculo (){
        return reserva;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getMarca_vehiculo() {
        return marca_vehiculo;
    }

    public void setMarca_vehiculo(String marca_vehiculo) {
        this.marca_vehiculo = marca_vehiculo;
    }

    public String getModelo_vehiculo() {
        return modelo_vehiculo;
    }

    public void setModelo_vehiculo(String modelo_vehiculo) {
        this.modelo_vehiculo = modelo_vehiculo;
    }

    public String getColor_vehiculo() {
        return color_vehiculo;
    }

    public void setColor_vehiculo(String color_vehiculo) {
        this.color_vehiculo = color_vehiculo;
    }

    public int getAnio_vehiculo() {
        return anio_vehiculo;
    }

    public void setAnio_vehiculo(int anio_vehiculo) {
        this.anio_vehiculo = anio_vehiculo;
    }

    public String getEstado_vehiculo() {
        return estado_vehiculo;
    }

    public void setEstado_vehiculo(String estado_vehiculo) {
        this.estado_vehiculo = estado_vehiculo;
    }

    public double getKilometraje_vehiculo() {
        return kilometraje_vehiculo;
    }

    public void setKilometraje_vehiculo(double kilometraje_vehiculo) {
        this.kilometraje_vehiculo = kilometraje_vehiculo;
    }

    public Sucursal getSucursal_origen() {
        return sucursal_origen;
    }

    public void setSucursal_origen(Sucursal sucursal_origen) {
        this.sucursal_origen = sucursal_origen;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}