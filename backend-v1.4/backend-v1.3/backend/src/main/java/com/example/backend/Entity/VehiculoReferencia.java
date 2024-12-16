package com.example.backend.Entity;

import jakarta.persistence.*;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

@Table(name = "vehiculo_referencia")
@Entity

public class VehiculoReferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVehiculoReferencia;//Identificador

    private String url; // Aquí agregas el campo URL

    private String modelo;

    @Lob
    private byte[] imagenVehiculoReferencia;

    private String descripcionPublicacion;
    private boolean estado;//disponibilidad
    private double CostoReservaVehiculo;
    private double CostoArriendolVehiculo;
    private int capacidadPasajeros;
    private int cantidadPuertas;
    private String tipoCombustible;
    private String mecanico_automatico;
    private String acriss;


    //precio, capacidad de pasajeros, cantidad de puertas, tipo de gasolina, mecánico o automático,
    public VehiculoReferencia(String descripcionPublicacion,
                              boolean estado,
                              double CostoReservaVehiculo,
                              double CostoArriendolVehiculo,
                              int capacidadPasajeros,
                              int cantidadPuertas,
                              String tipoCombustible,
                              String mecanico_automatico,
                              String acriss, String url) {
        this.descripcionPublicacion = descripcionPublicacion;
        this.estado = estado;
        this.CostoReservaVehiculo = CostoReservaVehiculo;
        this.CostoArriendolVehiculo = CostoArriendolVehiculo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.cantidadPuertas = cantidadPuertas;
        this.tipoCombustible = tipoCombustible;
        this.mecanico_automatico = mecanico_automatico;
        this.acriss = acriss;
        this.url = url;
    }

    public VehiculoReferencia() {

    }
// Getters
    public long getIdVehiculoReferencia() {
        return idVehiculoReferencia;
    }

    public String getDescripcionPublicacion() {
        return descripcionPublicacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public double getCostoReservaVehiculo() {
        return CostoReservaVehiculo;
    }

    public double getCostoArriendolVehiculo() {
        return CostoArriendolVehiculo;
    }

    public boolean isEstado() {
        return estado;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getCantidadPuertas() {
        return cantidadPuertas;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public String getMecanico_automatico() {
        return mecanico_automatico;
    }

    public String getAcriss() {
        return acriss;
    }

// Setters
    public void setIdVehiculoReferencia(long idPublicacion) {
        this.idVehiculoReferencia = idPublicacion;
    }

    public void setDescripcionPublicacion(String descripcionPublicacion) {
        this.descripcionPublicacion = descripcionPublicacion;
    }

    public void setEstado(boolean estadoPublicacion) {
        this.estado = estadoPublicacion;
    }

    public void setCostoReservaVehiculo(double precioPublicacion) {
        this.CostoReservaVehiculo = precioPublicacion;
    }

    public void setCostoArriendolVehiculo(double costoArriendolVehiculo) {
        CostoArriendolVehiculo = costoArriendolVehiculo;}

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public void setMecanico_automatico(String mecanico_automatico) {
        this.mecanico_automatico = mecanico_automatico;
    }

    public void setAcriss(String acriss) {
        this.acriss = acriss;
    }

    public void setModelo(String modelo) { this.modelo = modelo; }

    public void setUrl(String url){ this.url = url; }

    public void setImagenVehiculoReferencia(byte[] imagenVehiculoReferencia) {
        this.imagenVehiculoReferencia = imagenVehiculoReferencia;
    }

    public String getUrl() {
        return url;
    }

    public String getModelo() {
        return modelo;
    }

    public byte[] getImagenVehiculoReferencia() {
        return imagenVehiculoReferencia;
    }
}
