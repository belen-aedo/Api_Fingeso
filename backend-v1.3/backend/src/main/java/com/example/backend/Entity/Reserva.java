package com.example.backend.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "reservas")
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_reserva;

    @ManyToOne
    @JoinColumn(name = "ClienteId")
    private Cliente cliente; // Varias reservas asociadas a un cliente, Reserva-M------1-Cliente

    @ManyToOne
    @JoinColumn(name = "SucursalId")
    private Sucursal sucursal; // Varias reservas para una sucursal, una reserva pertenece a una sucursal, Reserva-M------1-Sucursal

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arriendId")
    private Arriendo arriendoAsignado;

    @ManyToOne(cascade = CascadeType.ALL)
    private VehiculoReferencia vehiculoAsignado; // Reserva-M-----1-VehículoReferencia

    private double CostoTotal;
    private boolean EstadoReserva;//Activa, Inactiva
    private LocalDate fechaInicioReserva;
    private LocalDate fechaTerminoReserva;
    private LocalDate FechaReserva;
    private Boolean ReservaFinalizada;
    private LocalDate FechaArriendoConcluido;


    public Reserva(long id_reserva, Cliente cliente, Sucursal sucursal, Arriendo arriendoAsignado, VehiculoReferencia vehiculoAsignado, int cantidadDias, double costoTotal, boolean estadoReserva, LocalDate fechaInicioReserva, LocalDate fechaTerminoReserva, LocalDate fechaReserva, Boolean reservaFinalizada, LocalDate fechaArriendoConcluido) {
        this.id_reserva = id_reserva;
        this.cliente = cliente;
        this.sucursal = sucursal;
        this.arriendoAsignado = arriendoAsignado;
        this.vehiculoAsignado = vehiculoAsignado;
        this.CostoTotal = costoTotal;
        this.EstadoReserva = estadoReserva;
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaTerminoReserva = fechaTerminoReserva;
        this.FechaReserva = fechaReserva;
        this.ReservaFinalizada = reservaFinalizada;
        this.FechaArriendoConcluido = fechaArriendoConcluido;
    }

    public Reserva() {
    }

   //Getters
    public long getId_reserva() {
        return id_reserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public Arriendo getArriendoAsignado() {
        return arriendoAsignado;
    }

    public VehiculoReferencia getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    public boolean isEstadoReserva() {
        return EstadoReserva;
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public LocalDate getFechaTerminoReserva() {
        return fechaTerminoReserva;
    }

    public LocalDate getFechaReserva() {
        return FechaReserva;
    }

    public Boolean getReservaFinalizada() {
        return ReservaFinalizada;
    }

    public LocalDate getFechaArriendoConcluido() {
        return FechaArriendoConcluido;
    }

    // Setters
    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public void setArriendoAsignado(Arriendo arriendoAsignado) {
        this.arriendoAsignado = arriendoAsignado;
    }

    public void setVehiculoAsignado(VehiculoReferencia vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }

    public void setCostoTotal(double costoTotal) {
        CostoTotal = costoTotal;
    }

    public void setEstadoReserva(boolean estadoReserva) {
        EstadoReserva = estadoReserva;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        this.fechaInicioReserva = fechaInicioReserva;
    }

    public void setFechaTerminoReserva(LocalDate fechaTerminoReserva) {
        this.fechaTerminoReserva = fechaTerminoReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        FechaReserva = fechaReserva;
    }

    public void setReservaFinalizada(Boolean reservaFinalizada) {
        ReservaFinalizada = reservaFinalizada;
    }

    public void setFechaArriendoConcluido(LocalDate fechaArriendoConcluido) {
        FechaArriendoConcluido = fechaArriendoConcluido;
    }
}

