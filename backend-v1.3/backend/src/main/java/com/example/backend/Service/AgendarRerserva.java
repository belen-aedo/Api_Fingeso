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

    /*
    public void realizarAgendamiento(Vehiculo vehiculoSeleccionado, String sucursalSeleccionadaR, String sucursalSeleccionadaD, LocalDate fechaR, LocalDate fechaD) {
    // 1. Filtrar los vehículos disponibles
    List<Vehiculo> vehiculosDisponibles = findByDates(fechaR, fechaD, sucursalSeleccionadaR);

    // 2. Verificar si el vehículo seleccionado está disponible
    boolean vehiculoDisponible = vehiculosDisponibles.stream()
            .anyMatch(v -> v.getId().equals(vehiculoSeleccionado.getId()));

    if (!vehiculoDisponible) {
        throw new IllegalStateException("El vehículo seleccionado no está disponible en las fechas indicadas.");
    }

    // 3. Crear la reserva
    Reserva nuevaReserva = new Reserva();
    nuevaReserva.setVehiculo(vehiculoSeleccionado);
    nuevaReserva.setSucursalRetiro(sucursalSeleccionadaR);
    nuevaReserva.setSucursalDevolucion(sucursalSeleccionadaD);
    nuevaReserva.setFechaInicioReserva(fechaR);
    nuevaReserva.setFechaTerminoReserva(fechaD);
    nuevaReserva.setReservaFinalizada(false);

    // Suponiendo que tienes un método para guardar la reserva
    reservaRepository.save(nuevaReserva);

    // 4. Crear el agendamiento de mantenimiento
    // Calcular los días de mantenimiento necesarios
    LocalDate fechaFinConMantenimiento = calcularNuevaFechaFin(fechaR, fechaD);

    // Crear el agendamiento para el mantenimiento
    Agendamiento nuevoAgendamiento = new Agendamiento();
    nuevoAgendamiento.setVehiculo(vehiculoSeleccionado);
    nuevoAgendamiento.setSucursal(sucursalSeleccionadaD); // Sucursal de devolución, por ejemplo
    nuevoAgendamiento.setFechaInicio(fechaD); // Comienza el día de la devolución
    nuevoAgendamiento.setProximaFechaDisponible(fechaFinConMantenimiento); // Fecha después del mantenimiento

    // Suponiendo que tienes un método para guardar el agendamiento
    agendamientoRepository.save(nuevoAgendamiento);

    // 5. (Opcional) Realizar otras tareas asociadas, como enviar notificaciones o actualizar el estado
}
    */




}