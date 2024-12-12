package com.example.backend.Entity;

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
    private Sucursal sucursal; // empleado-M---1-sucursal

    private String correoElectronico;
    private String password;

    // No-arg constructor
    public Empleado() { }

    public Empleado(long id, String nombre, String rut, String telefono, String rol, Sucursal sucursal, String correoElectronico, String password) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.rol = rol;
        this.sucursal = sucursal;
        this.correoElectronico = correoElectronico;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}