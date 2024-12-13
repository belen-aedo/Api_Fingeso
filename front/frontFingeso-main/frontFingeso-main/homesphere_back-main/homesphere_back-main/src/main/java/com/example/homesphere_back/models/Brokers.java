package com.example.homesphere_back.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Brokers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter

public class Brokers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    // Atributo relacionado al identificador de cada
    // usuario.
    private Long id;

    // Atributos relacionados a la información
    // básica de cada usuario.
    private String firstName;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private String rut;

    // Atributos relacionados al contacto de cada
    // usuario.
    private String email;
    private String number;

    // Atributos relacionados al inicio de sesión
    // de cada usuario.
    private String password;

    // Atributo relacionado a las visitas agendadas de los usuarios a las propiedades
    private String Visits;

    // Atributo que contiene los horarios disponibles del broker (ejemplo: "lunes 10:00-12:00; martes 14:00-16:00; ...")
    private String AvailableTimes;

    private String AvailableTimesBroker;

    private boolean document;

    private boolean isBroker = true;
}
