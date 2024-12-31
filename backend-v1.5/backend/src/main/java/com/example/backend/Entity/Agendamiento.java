package com.example.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Agendamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_agendamiento;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva; // Agendamiento M---

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo; // Agendamiento M---

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_sucursal_retiro")
    private Sucursal sucursalRetiro;

    @ManyToOne
    @JoinColumn(name = "id_sucursal_devolucion")
    private Sucursal sucursalDevolucion;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFinalizacion;

    @Column(nullable = false)
    private LocalDate ProximaFechaDisponible;

    //ESTADO AGENDAMIENTO, PROXIMO INCREMENTO

    public Agendamiento() {}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setId_agendamiento(Long id) {
        this.id_agendamiento = id;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public void setProximaFechaDisponible(LocalDate proximaFechaDisponible) {
        this.ProximaFechaDisponible = proximaFechaDisponible;
    }

    public void setSucursalRetiro(Sucursal sucursalRetiro) {
        this.sucursalRetiro = sucursalRetiro;
    }

    public void setSucursalDevolucion(Sucursal sucursalDevolucion) {
        this.sucursalDevolucion = sucursalDevolucion;
    }

    public Long getId_agendamiento() {
        return id_agendamiento;
    }

}
