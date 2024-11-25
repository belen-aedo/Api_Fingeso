package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "local_arriendo")
@Entity

public class Local_arriendo {
    @Id
    private String id_local;
    private String direccion_local;
    private String telefono_local;
    private String correo_local;
    private String estado_local;
    private int capacidad_local_vehiculos;

}
