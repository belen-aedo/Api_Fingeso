package com.example.backend.Entity;

import jakarta.persistence.*;

@Table(name = "vehiculo_referencia")
@Entity

public class VehiculoReferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVehiculoReferencia;//Identificador

    private String url; // Aquí agregas el campo URL

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String descripcionPublicacion;

    @Column(nullable = false)
    private double CostoReservaVehiculo;

    @Column(nullable = false)
    private double CostoArriendoVehiculo;

    @Column(nullable = false)
    private int capacidadPasajeros;

    @Column(nullable = false)
    private int cantidadPuertas;

    @Column(nullable = false)
    private String tipoCombustible;

    @Column(nullable = false)
    private String mecanico_automatico;

    @Column(nullable = false)
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
        this.CostoReservaVehiculo = CostoReservaVehiculo;
        this.CostoArriendoVehiculo = CostoArriendolVehiculo;
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

    public double getCostoReservaVehiculo() {
        return CostoReservaVehiculo;
    }

    public double getCostoArriendoVehiculo() {
        return CostoArriendoVehiculo;
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

    public void setCostoReservaVehiculo(double precioPublicacion) {
        this.CostoReservaVehiculo = precioPublicacion;
    }

    public void setCostoArriendoVehiculo(double costoArriendoVehiculo) {
        CostoArriendoVehiculo = costoArriendoVehiculo;}

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

    public String getUrl() {
        return url;
    }

    public String getModelo() {
        return modelo;
    }


}
