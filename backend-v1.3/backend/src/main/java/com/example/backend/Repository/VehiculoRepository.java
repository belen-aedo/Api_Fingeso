package com.example.backend.Repository;

import com.example.backend.Entity.Vehiculo;

import java.time.LocalDate;
import java.util.List;

public interface VehiculoRepository {
    Vehiculo findByPatente(String patente);
    List<Vehiculo> findBySucursal(String nombreSucursal);
    List<Vehiculo> findBySucursalFecha(LocalDate fechaRetiro, long idSucursalRetiro);
    public List<Vehiculo> findByDates(LocalDate fechaRetiro, LocalDate fechaDevolucion, String nombreSucursal);

}
