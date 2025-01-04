package com.example.backend.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sucursal" , uniqueConstraints = {
        @UniqueConstraint( columnNames = {"nombre_sucursal"})
})
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSucursal;

    @Column(unique = true , nullable = false)
    private String nombreSucursal;

    @Column(nullable = false)
    private String ciudadSucursal;

    @Column(nullable = false)
    private String telefonoSucursal;

    @OneToMany(mappedBy = "ubicacionActual", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehiculo> vehiculos; //Scurssal-1-----M-Vehiculos

    @OneToMany(mappedBy = "sucursalRetiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reserva> reservas; // Sucursal-1------M-Reservas

    public Sucursal() {}

    public Sucursal(long idSucursal, String ciudadSucursal, String telefonoSucursal) {
        this.idSucursal = idSucursal;
        this.ciudadSucursal = ciudadSucursal;
        this.telefonoSucursal = telefonoSucursal;
        this.vehiculos = new HashSet<>();
        this.reservas = new HashSet<>();
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

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
}
