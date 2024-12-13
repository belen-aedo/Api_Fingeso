package com.example.backend.Entity;

import jakarta.persistence.*;

@Table(name = "empleado")
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmpleado;

    private String nombre;
    private String rut;
    private String telefono;
    private String rol;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal; // empleado-M---1-sucursal

    private String email;
    private String password;

    // No-arg constructor
    public Empleado() { }

    public Empleado(String nombre, String rut, String telefono, String rol, Sucursal sucursal, String email, String password) {
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.rol = rol;
        this.sucursal = sucursal;
        this.email = email;
        this.password = password;
    }

    // getters
    public long getIdEmpleado() {
        return idEmpleado;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setIdEmpleado(long id) {
        this.idEmpleado = id;
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

    public void setEmail(String correoElectronico) {
        this.email = correoElectronico;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}