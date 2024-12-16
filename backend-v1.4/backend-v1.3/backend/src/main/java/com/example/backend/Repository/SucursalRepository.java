package com.example.backend.Repository;

import com.example.backend.Entity.Sucursal;

public interface SucursalRepository {
    Sucursal findById(long id);
    Sucursal findByNombreSucursal(String nombre);

}
