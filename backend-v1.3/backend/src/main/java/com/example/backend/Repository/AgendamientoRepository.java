package com.example.backend.Repository;

import com.example.backend.Entity.Agendamiento;
import com.example.backend.Entity.Reserva;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.VehiculoReferencia;

import java.time.LocalDate;

public interface AgendamientoRepository {
    //solo una idea

    /**
     *
     */
    public void save(LocalDate FechaInicio, LocalDate FcehaFin, Sucursal sRetiro, Sucursal sDevolucion);
    public Agendamiento findAgendamientoByIdUsuario(long idUsuario);
    public Agendamiento findAgendamientoByIdReserva(long idReserva);

}


/*
// 1 semana de arriendo: 3 dias de mantención
// menos de 1 semana: 1 dia de mantención
// entre 1 semana y 2 semanas: 4 dias de mantención
// entre 2 semana y 3 semanas: 5 dias de mantención
// más de 3 semanas: 1 semana de mantención
// 1 mes: máximo de arriendo

------------------------------------------------------------------------------------------------------------------
Version 1:
ingrese sucursal: XXXX
----> Ingrese fecha retiro: DD/MM/2024 y fecha de devolución
------> Mostrar vehiculos en ese tiempo(Filtros)
--------> Hecho agendaminto
------> Ingrese lugar de retiro (misma sucursal o diferente)
 */