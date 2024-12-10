package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "sucursal")
@Entity
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_sucursal; // Identificador del vehículo
    private String ciudad_sucursal;
    private String telefono_sucursal;
    private int cantidad_vehiculos_disponibles;


    @ManyToMany
    private Set<Vehiculo> vehiculos;

    @ManyToMany
    private Set<Reserva> reservas;

    public long getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(long id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getCiudad_sucursal() {
        return ciudad_sucursal;
    }

    public void setCiudad_sucursal(String ciudad_sucursal) {
        this.ciudad_sucursal = ciudad_sucursal;
    }

    public String getTelefono_sucursal() {
        return telefono_sucursal;
    }

    public void setTelefono_sucursal(String telefono_sucursal) {
        this.telefono_sucursal = telefono_sucursal;
    }

    public int getCantidad_vehiculos_disponibles() {
        return cantidad_vehiculos_disponibles;
    }

    public void setCantidad_vehiculos_disponibles(int cantidad_vehiculos_disponibles) {
        this.cantidad_vehiculos_disponibles = cantidad_vehiculos_disponibles;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Reserva getReserva(int id) {
        for (Reserva reserva : reservas) {
            if(reserva.getId_reserva() == id){
                return reserva;
            }
        }
        return null;
    }
}