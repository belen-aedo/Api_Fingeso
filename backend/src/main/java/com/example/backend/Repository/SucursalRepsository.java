package com.example.backend.Repository;

import com.example.backend.Entity.Sucursal;

public interface SucursalRepsository {
    Sucursal findById(long id);
    Sucursal findByNombreSucursal(String nombre);

}
