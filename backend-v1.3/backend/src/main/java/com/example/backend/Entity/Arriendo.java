package com.example.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "arriendo")
@Entity
public class Arriendo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idArriedno;//Identificador

    private String rut; // DNI del cliente

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo; // arriendo-1------1-vehiculo

    private double CostoTotal;
    private LocalDate fechaPagoArriendo;
    private boolean EstadoArriendo;

    public Arriendo(long idArriedno, String rut, Vehiculo vehiculo, double costoTotal, LocalDate fechaPagoArriendo, boolean estadoArriendo) {
        this.idArriedno = idArriedno;
        this.rut = rut;
        this.vehiculo = vehiculo;
        this.CostoTotal = costoTotal;
        this.fechaPagoArriendo = fechaPagoArriendo;
        this.EstadoArriendo = estadoArriendo;
    }

    public Arriendo() {}

    public long getIdArriedno() {
        return idArriedno;
    }

    public String getRut() {
        return rut;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    public LocalDate getFechaPagoArriendo() {
        return fechaPagoArriendo;
    }

    public boolean isEstadoArriendo() {
        return EstadoArriendo;
    }

    public void setIdArriedno(long idArriedno) {
        this.idArriedno = idArriedno;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setCostoTotal(double costoTotal) {
        CostoTotal = costoTotal;
    }

    public void setFechaPagoArriendo(LocalDate fechaPagoArriendo) {
        this.fechaPagoArriendo = fechaPagoArriendo;
    }

    public void setEstadoArriendo(boolean estadoArriendo) {
        EstadoArriendo = estadoArriendo;
    }
}