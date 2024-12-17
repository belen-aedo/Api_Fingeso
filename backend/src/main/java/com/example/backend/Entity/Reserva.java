package com.example.backend.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Table(name = "reservas")
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_reserva;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente; // Varias reservas asociadas a un cliente, Reserva-M------1-Cliente
    @ManyToOne
    @JoinColumn(name = "id_Sucursal_retiro")
    private Sucursal sucursalRetiro;// Varias reservas para una sucursal, una reserva pertenece a una sucursal, Reserva-M------1-Sucursal
    @ManyToOne
    @JoinColumn(name = "id_sucursal_devolucion")
    private Sucursal sucursalDevolucion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_arriendo")
    private Arriendo arriendoAsignado;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vehiculo_referencia")
    private VehiculoReferencia vehiculoAsignado; // Reserva-M-----1-VehículoReferencia
    private double CostoTotal;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaTerminoReserva;
    private LocalDate FechaReserva;
    private Boolean ReservaFinalizada;
    private Boolean pagoReserva;

    public Reserva(long id_reserva,
                   Cliente cliente,
                   Sucursal sucursalRetiro,
                   Arriendo arriendoAsignado,
                   VehiculoReferencia vehiculoAsignado,
                   double costoTotal,
                   boolean pagoReserva,
                   LocalDate fechaInicioReserva,
                   LocalDate fechaTerminoReserva,
                   LocalDate fechaReserva,
                   Boolean reservaFinalizada) {
        this.id_reserva = id_reserva;
        this.cliente = cliente;
        this.sucursalRetiro = sucursalRetiro;
        this.arriendoAsignado = arriendoAsignado;
        this.vehiculoAsignado = vehiculoAsignado;
        this.CostoTotal = costoTotal;
        this.pagoReserva = pagoReserva;
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaTerminoReserva = fechaTerminoReserva;
        this.FechaReserva = fechaReserva;
        this.ReservaFinalizada = reservaFinalizada;
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

    public Sucursal getSucursalRetiro() {
        return sucursalRetiro;
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

    public Sucursal getSucursalDevolucion() {
        return sucursalDevolucion;
    }

    public Boolean getPagoReserva() {
        return pagoReserva;
    }

    // Setters
    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setSucursalRetiro(Sucursal sucursal) {
        this.sucursalRetiro = sucursal;
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

    public void setSucursalDevolucion(Sucursal sucursalDevolucion) {
        this.sucursalDevolucion = sucursalDevolucion;
    }

    public void setPagoReserva(Boolean pagoReserva) {
        this.pagoReserva = pagoReserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id_reserva == reserva.id_reserva;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_reserva);
    }
}

