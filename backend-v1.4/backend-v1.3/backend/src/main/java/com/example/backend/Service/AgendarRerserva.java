package com.example.backend.Service;

import com.example.backend.Entity.Agendamiento;
import com.example.backend.Entity.Reserva;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
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
    VehiculoRepository vehiculoRepository;

    @Autowired
    SucursalRepository sucursalRepository;

    public void realizarAgendamiento (Vehiculo vehiculoSeleccionado, String SucursalSeleccionadaR, String SucursalSeleccionadaD, LocalDate fechaR, LocalDate fechaD ) {

        Reserva NuevaReserva = new Reserva();
        Agendamiento NuevoAgendamiento = new Agendamiento();
        NuevoAgendamiento.setVehiculo(vehiculoSeleccionado);



    }

}