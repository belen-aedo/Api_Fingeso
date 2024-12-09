package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_usu_registrado; // Identificador
    private String rut_registrado; // DNI del cliente
    private String nombre_registrado; // Nombre del cliente
    private String direccion_registrado; // Dirección del cliente
    private String telefono_registrado; // Teléfono del cliente
    private String correo_registrado; // Correo electrónico del cliente
    private String password; // Contraseña del cliente
    private LocalDate fecha_nacimiento_cliente; // Fecha de nacimiento del cliente

    //Constructores


    public Cliente(String rut_registrado, String nombre_registrado, String direccion_registrado, String telefono_registrado, String correo_registrado, String password, LocalDate fecha_nacimiento_cliente) {
        this.id_usu_registrado = id_usu_registrado;
        this.rut_registrado = rut_registrado;
        this.nombre_registrado = nombre_registrado;
        this.direccion_registrado = direccion_registrado;
        this.telefono_registrado = telefono_registrado;
        this.correo_registrado = correo_registrado;
        this.password = password;
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
    }

    public Cliente() {

    }

    //Getters y Setters
    public long getId_usu_registrado() {return id_usu_registrado;}

    public void setId_usu_registrado(long id_usu_registrado) {
        this.id_usu_registrado = id_usu_registrado;
    }

    public String getRut_registrado() {
        return rut_registrado;
    }

    public void setRut_registrado(String rut_registrado) {
        this.rut_registrado = rut_registrado;
    }

    public String getNombre_registrado() {
        return nombre_registrado;
    }

    public void setNombre_registrado(String nombre_registrado) {
        this.nombre_registrado = nombre_registrado;
    }

    public String getDireccion_registrado() {
        return direccion_registrado;
    }

    public void setDireccion_registrado(String direccion_registrado) {
        this.direccion_registrado = direccion_registrado;
    }

    public String getTelefono_registrado() {
        return telefono_registrado;
    }

    public void setTelefono_registrado(String telefono_registrado) {
        this.telefono_registrado = telefono_registrado;
    }

    public String getCorreo_registrado() {
        return correo_registrado;
    }

    public void setCorreo_registrado(String correo_registrado) {
        this.correo_registrado = correo_registrado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contrasena_registrado) {
        this.password = contrasena_registrado;
    }

    public LocalDate getFecha_nacimiento_cliente() {
        return fecha_nacimiento_cliente;
    }

    public void setFecha_nacimiento_cliente(LocalDate fecha_nacimiento_cliente) {
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
    }

}