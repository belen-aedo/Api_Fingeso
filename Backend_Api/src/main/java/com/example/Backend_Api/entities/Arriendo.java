package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Table(name = "arriendo")
@Entity
public class Arriendo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_arriendo;//Identificador

    private String rut_cliente; // DNI del cliente

    @OneToOne
    private Vehiculo vehiculo; //llaves foraneas Significa que un Arriendo esta relacionado con un solo Vehículo.

    @ManyToOne
    private Sucursal local; //llaves foraneas Significa que muchos Arriendo pueden estar relacionados con una sola sucursal.



    private double monto_total;
    private LocalDate fecha_inicio_arriendo;
    private LocalDate fecha_termino_arriendo;
    private boolean estado_arriendo;//Activa( != 0), Inactiva(0)

    public long getId_arriendo() {
        return id_arriendo;
    }

    public void setId_arriendo(long id_arriendo) {
        this.id_arriendo = id_arriendo;
    }

    public String getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Sucursal getLocal() {
        return local;
    }

    public void setLocal(Sucursal local) {
        this.local = local;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public LocalDate getFecha_inicio_arriendo() {
        return fecha_inicio_arriendo;
    }

    public void setFecha_inicio_arriendo(LocalDate fecha_inicio_arriendo) {
        this.fecha_inicio_arriendo = fecha_inicio_arriendo;
    }

    public LocalDate getFecha_termino_arriendo() {
        return fecha_termino_arriendo;
    }

    public void setFecha_termino_arriendo(LocalDate fecha_termino_arriendo) {
        this.fecha_termino_arriendo = fecha_termino_arriendo;
    }

    public boolean isEstado_arriendo() {
        return estado_arriendo;
    }

    public void setEstado_arriendo(boolean estado_arriendo) {
        this.estado_arriendo = estado_arriendo;
    }


}


