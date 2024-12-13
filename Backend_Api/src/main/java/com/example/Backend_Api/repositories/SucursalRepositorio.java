package com.example.Backend_Api.repositories;

import com.example.Backend_Api.entities.Sucursal;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursal, Long>{
    Sucursal findByciudad_sucursal(String ciudad_sucursal);
    Sucursal findBycantidad_vehiculos_disponibles(int cantidad_vehiculos_disponible);
    Sucursal findByid_sucursal(long id_sucursal);
    Sucursal save (Sucursal sucursal);
}
