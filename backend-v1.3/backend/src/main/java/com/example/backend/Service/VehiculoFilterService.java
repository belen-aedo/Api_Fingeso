package com.example.backend.Service;

import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
import com.example.backend.Repository.SucursalRepository;
import com.example.backend.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

}
