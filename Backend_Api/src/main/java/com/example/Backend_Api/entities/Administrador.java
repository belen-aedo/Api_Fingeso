package com.example.Backend_Api.entities;

import jakarta.persistence.*;

@Table(name = "administrador")
@Entity

public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_admin;//Identificador
    private String rut_administrador;
    private String nombre_administrador;
    private String apellido_administrador;
    private String direccion_administrador;
    private String telefono_administrador;
    private String correo_administrador;
    private String contrasena_administrador;
    private String lugar_de_trabajo_administrador;//local

    public long getId_admin() {
        return id_admin;
    }

    public void setId_admin(long id_admin) {
        this.id_admin = id_admin;
    }

    public String getRut_administrador() {
        return rut_administrador;
    }

    public void setRut_administrador(String rut_administrador) {
        this.rut_administrador = rut_administrador;
    }

    public String getNombre_administrador() {
        return nombre_administrador;
    }

    public void setNombre_administrador(String nombre_administrador) {
        this.nombre_administrador = nombre_administrador;
    }

    public String getApellido_administrador() {
        return apellido_administrador;
    }

    public void setApellido_administrador(String apellido_administrador) {
        this.apellido_administrador = apellido_administrador;
    }

    public String getDireccion_administrador() {
        return direccion_administrador;
    }

    public void setDireccion_administrador(String direccion_administrador) {
        this.direccion_administrador = direccion_administrador;
    }

    public String getTelefono_administrador() {
        return telefono_administrador;
    }

    public void setTelefono_administrador(String telefono_administrador) {
        this.telefono_administrador = telefono_administrador;
    }

    public String getCorreo_administrador() {
        return correo_administrador;
    }

    public void setCorreo_administrador(String correo_administrador) {
        this.correo_administrador = correo_administrador;
    }

    public String getContrasena_administrador() {
        return contrasena_administrador;
    }

    public void setContrasena_administrador(String contrasena_administrador) {
        this.contrasena_administrador = contrasena_administrador;
    }

    public String getLugar_de_trabajo_administrador() {
        return lugar_de_trabajo_administrador;
    }

    public void setLugar_de_trabajo_administrador(String lugar_de_trabajo_administrador) {
        this.lugar_de_trabajo_administrador = lugar_de_trabajo_administrador;
    }


}

