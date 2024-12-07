package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehiculo")
@Entity
public class Vehiculo{
    @Id
    private String placa_vehiculo;//Placa del vehículo
    private String marca_vehiculo;//Marca del vehículo
    private String modelo_vehiculo;//Modelo del vehículo
    private String color_vehiculo; //Color del vehículo
    private int anio_vehiculo; //Año de fabricación
    private String estado_vehiculo;//Disponible, Ocupado, Mantenimiento
    private double kilometraje_vehiculo;//Kilometraje del vehículo

    @ManyToOne
    private Sucursal sucursal_origen;
}
