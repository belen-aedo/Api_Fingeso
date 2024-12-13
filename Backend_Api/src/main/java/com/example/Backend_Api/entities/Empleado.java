package com.example.Backend_Api.entities;

import jakarta.persistence.*;

@Table(name = "empleado")
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String rut;
    private String telefono;
    private String rol;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
    private String correoElectronico;
    private String password;

    // No-arg constructor
    public Empleado() {
    }

    // Constructor
    public Empleado(String nombre, String rut, String telefono, String rol, Sucursal sucursal, String correoElectronico, String password) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.rol = rol;
        this.sucursal = sucursal;
        this.correoElectronico = correoElectronico;
        this.password = password;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void devolucion(int id) {
        String ocupado = "Ocupado";
        sucursal.getReserva(id).getVehiculo().setEstado_vehiculo(ocupado);
    }


}