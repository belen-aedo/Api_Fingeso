package com.example.backend.Service;

import com.example.backend.Entity.*;
import com.example.backend.Repository.AgendamientoRepository;
import com.example.backend.Repository.SucursalRepository;
import com.example.backend.Repository.VehiculoRepository;
import com.example.backend.Utilidades.ValidacionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AgendarRerserva {

    // aqui se haria el metodo para realizar el agendamiento

    @Autowired
    AgendamientoRepository agendamientoRepository;

    public void realizarAgendamiento (Vehiculo vehiculoSeleccionado,
                                      Sucursal SucursalSeleccionadaR,
                                      Sucursal SucursalSeleccionadaD,
                                      LocalDate fechaR,
                                      LocalDate fechaD,
                                      LocalDate fechaDispo,
                                      Reserva reserva, Cliente cliente) {
        agendamientoRepository.save(fechaR, fechaD, fechaDispo, SucursalSeleccionadaR, SucursalSeleccionadaD, cliente, vehiculoSeleccionado, reserva );
    }

}