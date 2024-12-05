package com.example.Backend_Api.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_pago;//Identificador
    private double monto_pago;
    private List<String> metodo_pago;
    private LocalDate fecha_pago;
    private boolean estado_pago;//Pagado, Pendiente

}
