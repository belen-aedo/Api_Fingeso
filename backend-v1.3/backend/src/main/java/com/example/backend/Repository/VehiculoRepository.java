package com.example.backend.Repository;

import com.example.backend.Entity.Vehiculo;

import java.util.List;

public interface VehiculoRepository {
    Vehiculo findByPatente(String patente);
    List<Vehiculo> findBySucursal(String nombreSucursal);
    List<Vehiculo> ObtenerPorDisponibilidad(String placa);

}
