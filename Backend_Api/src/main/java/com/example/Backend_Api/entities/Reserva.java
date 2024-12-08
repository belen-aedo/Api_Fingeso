package com.example.Backend_Api.entities;

import jakarta.persistence.*;

@Table(name = "reservas")
@Entity


public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_reserva;//Identificador del vehículo
    @ManyToOne
    private Cliente usuarioRegistrado; //llaves foraneas
    @ManyToOne
    private Vehiculo vehiculo;//llaves foraneas
    @ManyToOne
    private Sucursal local ;//llaves foraneas

    private int cantidad_dias;
    private int valor_total;
    private boolean estado_reserva;//Activa, Inactiva
    private String fecha_inicio_reserva;
    private String fecha_termino_reserva;
    private String fecha_reserva;
    private String hora_reserva;

    public long getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Cliente getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuarioRegistrado(Cliente usuarioRegistrado) {
        this.usuarioRegistrado = usuarioRegistrado;
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

    public int getCantidad_dias() {
        return cantidad_dias;
    }

    public void setCantidad_dias(int cantidad_dias) {
        this.cantidad_dias = cantidad_dias;
    }

    public int getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }

    public boolean isEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(boolean estado_reserva) {
        this.estado_reserva = estado_reserva;
    }

    public String getFecha_inicio_reserva() {
        return fecha_inicio_reserva;
    }

    public void setFecha_inicio_reserva(String fecha_inicio_reserva) {
        this.fecha_inicio_reserva = fecha_inicio_reserva;
    }

    public String getFecha_termino_reserva() {
        return fecha_termino_reserva;
    }

    public void setFecha_termino_reserva(String fecha_termino_reserva) {
        this.fecha_termino_reserva = fecha_termino_reserva;
    }

    public String getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getHora_reserva() {
        return hora_reserva;
    }

    public void setHora_reserva(String hora_reserva) {
        this.hora_reserva = hora_reserva;
    }
}


