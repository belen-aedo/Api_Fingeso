package com.example.backend.Service;

import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
import com.example.backend.Entity.VehiculoReferencia;
import com.example.backend.Repository.SucursalRepository;
import com.example.backend.Repository.VehiculoRepository;
import com.example.backend.Repository.VehiculoRepositoryImplement;
import com.example.backend.Utilidades.Pair;
import com.example.backend.Utilidades.ValidacionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    //Retorno un par con los vehiculos disponibles y sus referencias

    public List<Vehiculo> ObtenerVehiculosDisponibles(LocalDate fechaInicio, LocalDate fechaFin, String sRetiro) {
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
        List<Vehiculo> vehiculosDisponibles = vehiculoRepository.findByDates(fechaInicio, fechaProximaDisponibilidad, sRetiro);

        /*
        //.6 Obtener modelos únicos
        List<String> modelos = vehiculosDisponibles.stream()
                .map(Vehiculo::getModelo)
                .distinct() // Filtra duplicados
                .toList();

        // Obtener referencias en una sola consulta
        List<VehiculoReferencia> referencias = vehiculoRepository.getReferenciasPorModelos(modelos);
        */

        return vehiculosDisponibles;
    }

    public void obtenerVehiculosReferencia(List<Vehiculo> vehiculos) {
        // Obtener modelos únicos
        List<String> modelos = vehiculos.stream()
                .map(Vehiculo::getModelo)
                .distinct() // Filtra duplicados
                .toList();

        // Obtener referencias por cada modelo y almacenarlas
        List<VehiculoReferencia> referencias = new ArrayList<>();
        modelos.forEach(modelo -> {
            VehiculoReferencia referencia = vehiculoRepository.getReferencias(modelo);
            if (referencia != null) { // Manejo de posibles nulos
                referencias.add(referencia);
            }
        });
        // Aquí puedes realizar cualquier operación adicional con las referencias
        referencias.forEach(System.out::println); // Ejemplo: imprimir referencias
    }



}
