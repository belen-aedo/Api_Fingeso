package com.example.backend.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Table(name = "sucursal")
@Entity
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSucursal;

    private String ciudadSucursal;
    private String telefonoSucursal;
    private int CantidadVehiculosDisponibles;

    @OneToMany(mappedBy = "ubicacionActual", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehiculo> vehiculos; //Scurssal-1-----M-Vehiculos

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reserva> reservas; // Sucursal-1------M-Reservas

    public Sucursal() {
    }

    public Sucursal(long idSucursal, String ciudadSucursal, String telefonoSucursal, int cantidadVehiculosDisponibles, Set<Vehiculo> vehiculos, Set<Reserva> reservas) {
        this.idSucursal = idSucursal;
        this.ciudadSucursal = ciudadSucursal;
        this.telefonoSucursal = telefonoSucursal;
        this.CantidadVehiculosDisponibles = cantidadVehiculosDisponibles;
        this.vehiculos = vehiculos;
        this.reservas = reservas;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public String getCiudadSucursal() {
        return ciudadSucursal;
    }

    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    public int getCantidadVehiculosDisponibles() {
        return CantidadVehiculosDisponibles;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setCiudadSucursal(String ciudadSucursal) {
        this.ciudadSucursal = ciudadSucursal;
    }

    public void setTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }

    public void setCantidadVehiculosDisponibles(int cantidadVehiculosDisponibles) {
        CantidadVehiculosDisponibles = cantidadVehiculosDisponibles;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
}
