package com.example.backend.Entity;

import jakarta.persistence.*;

import java.security.PrivateKey;
import java.time.LocalDate;

@Table(name = "arriendo")
@Entity
public class Arriendo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idArriedno;//Identificador

    private String rutCliente; // DNI del cliente

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo; // arriendo-1------1-vehiculo

    @ManyToOne
    @JoinColumn(name = "id_Sucursal_retiro")
    private Sucursal sucursalRetiro;// Varias reservas para una sucursal, una reserva pertenece a una sucursal, Reserva-M------1-Sucursal

    @ManyToOne
    @JoinColumn(name = "id_sucursal_devolucion")
    private Sucursal sucursalDevolucion;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    private LocalDate fechaArriendo;
    private LocalDate fechaInicioArriendo;
    private LocalDate FechaTerminoArriendo;

    private double CostoTotal;
    private boolean EstadoArriendo;

    public Arriendo(String rutCliente, Vehiculo vehiculo, double costoTotal, LocalDate fechaArriendo, boolean estadoArriendo, LocalDate fechaInicioArriendo, LocalDate FechaTerminoArriendo) {
        this.rutCliente = rutCliente;
        this.vehiculo = vehiculo;
        this.CostoTotal = costoTotal;
        this.fechaArriendo = fechaArriendo;
        this.EstadoArriendo = estadoArriendo;
        this.fechaInicioArriendo = fechaInicioArriendo;
        this.FechaTerminoArriendo = FechaTerminoArriendo;
    }

    public Arriendo() {}

    // getters
    public long getIdArriedno() {
        return idArriedno;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    public LocalDate getFechaArriendo() {
        return fechaArriendo;
    }

    public LocalDate getFechaInicioArriendo() {
        return fechaInicioArriendo;
    }

    public LocalDate getFechaTerminoArriendo() {
        return FechaTerminoArriendo;
    }

    public boolean getEstadoArriendo() {
        return EstadoArriendo;
    }

    // setters
    public void setIdArriendo(long idArriedno) {
        this.idArriedno = idArriedno;
    }

    public void setRutCliente(String rut) {
        this.rutCliente = rut;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setCostoTotal(double costoTotal) {
        CostoTotal = costoTotal;
    }

    public void setFechaArriendo(LocalDate fechaPagoArriendo) {
        this.fechaArriendo = fechaPagoArriendo;
    }

    public void setEstadoArriendo(boolean estadoArriendo) {
        EstadoArriendo = estadoArriendo;
    }

    public void setFechaInicioArriendo(LocalDate fechaInicioArriendo) {
        this.fechaInicioArriendo = fechaInicioArriendo;
    }

    public void setFechaTerminoArriendo(LocalDate fechaTerminoArriendo) {
        FechaTerminoArriendo = fechaTerminoArriendo;
    }
}