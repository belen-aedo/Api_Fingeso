package com.example.Backend_Api.repositories;

import com.example.Backend_Api.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{
    Vehiculo findByPlaca_vehiculo(String placa_vehiculo);
    List<Vehiculo> findByMarca_vehiculo(String marca_vehiculo);
    List<Vehiculo> findByModelo_vehiculo(String modelo_vehiculo);

    // encontrar por fecha disponibles metodo, pero debe devolver la lista de los vehículos disponibles por fecha.

    Vehiculo save (Vehiculo vehiculo);
}
