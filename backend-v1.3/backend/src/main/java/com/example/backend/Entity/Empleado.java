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
    private Sucursal sucursal;

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
}