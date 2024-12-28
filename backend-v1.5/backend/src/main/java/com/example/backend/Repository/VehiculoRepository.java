package com.example.backend.Repository;

import com.example.backend.Entity.Vehiculo;
import com.example.backend.Entity.VehiculoReferencia;

import java.time.LocalDate;
import java.util.List;

public interface VehiculoRepository {
    Vehiculo findByPatente(String patente);
    List<Vehiculo> findBySucursal(String nombreSucursal);
    List<Vehiculo> findByDates(LocalDate fechaRetiro, LocalDate fechaDevolucion, String nombreSucursal);
    VehiculoReferencia getReferencias(String modelo);
    List<VehiculoReferencia> getReferenciasPorModelos(List<String> modelos);
    Vehiculo getVehiculoByIdReserva(Long id);

}
