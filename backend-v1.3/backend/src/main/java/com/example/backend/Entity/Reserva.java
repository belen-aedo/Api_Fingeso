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

    private int CantidadDias;
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
        this.CantidadDias = cantidadDias;
        this.CostoTotal = costoTotal;
        this.EstadoReserva = estadoReserva;
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaTerminoReserva = fechaTerminoReserva;
        this.FechaReserva = fechaReserva;
        this.ReservaFinalizada = reservaFinalizada;
        this.FechaArriendoConcluido = fechaArriendoConcluido;
    }
}

