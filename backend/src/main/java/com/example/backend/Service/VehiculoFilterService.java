package com.example.backend.Service;

import com.example.backend.Entity.Vehiculo;
import com.example.backend.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoFilterService {

    @Autowired
    VehiculoRepository vehiculoRepository;

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
}
