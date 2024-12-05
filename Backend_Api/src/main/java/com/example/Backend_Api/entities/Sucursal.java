package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sucural")
@Entity

public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_sucursal;//Identificador del vehículo
    private String ciudad_sucursal;
    private String telefono_sucursal;
    private int cantidad_vehiculos_disponibles;
    @OneToMany
    private List<Vehiculo> vehiculos;

}
