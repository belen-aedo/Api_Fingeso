package com.example.backend.Service;

import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
import com.example.backend.Repository.SucursalRepository;
import com.example.backend.Repository.VehiculoRepository;
import com.example.backend.Utilidades.ValidacionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoFilterService {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    public Vehiculo BuscarVehiculoPorPatente(String patente) {
            Vehiculo vehiculo = vehiculoRepository.findByPatente(patente);
            if (vehiculo == null) {
                throw new IllegalArgumentException("vehiculo no encontrado por la patente: " + patente);
            }
            return vehiculo;
    }

    /**
     * Se obtiene una lista de todos los vehículos que se encuentran en la sucursal indicada
     * @param nombreSucursal nombre de una sucursal
     * @return una lista de todos los vehiculos que estan en cierta sucursal
     */
    public List<Vehiculo> ObtenerVehiculosPorSucursal(String nombreSucursal) {
        List<Vehiculo> vehiculos = vehiculoRepository.findBySucursal(nombreSucursal);
        if (vehiculos.isEmpty()) {
            throw new IllegalArgumentException("la sucursal indicada no tiene vehículos: " + nombreSucursal);
        }
        return vehiculos;
    }


    /**
    public List<Vehiculo> ObtenerVehiculosDisponibles(String nombreScursalR, String nombreSucursalD, LocalDate fechaRetiro, LocalDate fechaDevo) {
        List<Vehiculo> vehiculosDisponibles = new ArrayList<>();
        for(Vehiculo vehiculo : vehiculoRepository.findBySucursal(nombreSucursalD)) {
            //solo los que no tengan reserva y esten disponibles, tambien le tema de las fecha
            if ( (!(vehiculo.getReserva() == null) && vehiculo.getReserva().getArriendoAsignado().getFechaTerminoArriendo().plusWeeks(1).isBefore(fechaRetiro)) || (vehiculo.getReserva()==null && vehiculo.getEstadoVehiculo().equals("D"))){
                vehiculosDisponibles.add(vehiculo);
            }
        }
        if (vehiculosDisponibles.isEmpty()){
            throw new IllegalArgumentException("la sucursal indicada no tiene vehículos disponibles: " + nombreSucursalD);
        }
        return vehiculosDisponibles;
    }
    */

    public List<Vehiculo> ObtenerVehiculosDisponibles(String nombreSucursalR, LocalDate fechaRetiro){
        Sucursal sucursal = sucursalRepository.findByNombreSucursal(nombreSucursalR);
        return vehiculoRepository.findBySucursalFecha(fechaRetiro, sucursal.getIdSucursal());
    }


    public List<Vehiculo> ObtenerVehiculo(LocalDate fechaInicio, LocalDate fechaFin, String sRetiro) {

        // 1. Validar la duración del arriendo (no puede superar los 30 días)
        if (ChronoUnit.DAYS.between(fechaInicio, fechaFin) > 30) {
            throw new IllegalArgumentException("El arriendo no puede superar los 30 días.");
        }

        // 2. Validar que la fecha de inicio este antes de la fin
        if(!fechaInicio.isBefore(fechaFin) ) {
            throw new IllegalArgumentException("Fechas de devolución no puede estar antes de la fecha de retiro");
        };

        // 3. Validar que la fecha inicio sea mayor o igual
        LocalDate fechaActual = LocalDate.now();

        // 4. Verifica que la fecha de inicio sea igual o posterior a la fecha actual
        if (!(fechaInicio.isAfter(fechaActual) || fechaInicio.isEqual(fechaActual))) {
            throw new IllegalArgumentException("la fecha debe estar coordinada con la fecha actual");
        }
        ValidacionDatos validacionDatos = new ValidacionDatos();
        LocalDate fechaProximaDisponibilidad  = validacionDatos.calcularNuevaFechaFin( fechaInicio, fechaFin );

        // 5. obtener los vehiculo que están disponibles.
        return vehiculoRepository.findByDates(fechaInicio, fechaProximaDisponibilidad, sRetiro);
    }

    /*
    public boolean crearAgendamiento(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, Sucursal sucursalRetiro, Sucursal sucursalDevolucion) {
    // Paso 1: Verificar la disponibilidad del vehículo utilizando el nuevo método fusionado
    List<Vehiculo> vehiculosDisponibles = findByDates(fechaInicio, fechaFin, sucursalRetiro.getNombreSucursal());

    // Paso 2: Verificar si el vehículo está en la lista de vehículos disponibles
    if (!vehiculosDisponibles.contains(vehiculo)) {
        return false; // El vehículo no está disponible en el rango de fechas
    }

    // Paso 3: Calcular la nueva fecha de fin con los días de mantenimiento
    LocalDate nuevaFechaFin = calcularNuevaFechaFin(fechaInicio, fechaFin);

    // Paso 4: Verificar que no haya solapamientos con otros agendamientos
    if (!verificarSolapamientos(vehiculo, fechaInicio, nuevaFechaFin)) {
        return false; // Existen solapamientos con otros agendamientos
    }

    // Paso 5: Crear el agendamiento
    Agendamiento agendamiento = new Agendamiento();
    agendamiento.setVehiculo(vehiculo);
    agendamiento.setFechaInicio(fechaInicio);
    agendamiento.setFechaFin(nuevaFechaFin);
    agendamiento.setSucursalRetiro(sucursalRetiro);
    agendamiento.setSucursalDevolucion(sucursalDevolucion);
    agendamiento.setProximaFechaDisponible(nuevaFechaFin.plusDays(7)); // Ejemplo de próxima fecha disponible

    // Guardar el agendamiento en la base de datos
    agendamientoRepository.save(agendamiento);

    return true; // Agendamiento creado exitosamente
}

private LocalDate calcularNuevaFechaFin(LocalDate fechaInicio, LocalDate fechaFin) {
    // Calcular la nueva fecha de fin con días de mantenimiento
    int diasMantenimiento = calcularDiasMantenimiento(fechaInicio, fechaFin);
    return fechaFin.plusDays(diasMantenimiento); // Retornar la nueva fecha de fin con mantenimiento
}

private boolean verificarSolapamientos(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
    // Verificar si existe algún solapamiento con otros agendamientos
    List<Agendamiento> agendamientos = agendamientoRepository.findByVehiculo(vehiculo);

    for (Agendamiento agendamiento : agendamientos) {
        if (!(fechaFin.isBefore(agendamiento.getFechaInicio()) || fechaInicio.isAfter(agendamiento.getProximaFechaDisponible()))) {
            return false; // Solapamiento encontrado
        }
    }

    return true; // No hay solapamientos
}

    */

}
