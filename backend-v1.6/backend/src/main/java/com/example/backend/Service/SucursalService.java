package com.example.backend.Service;

import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
import com.example.backend.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {

    @Autowired
    SucursalRepository sucursal;

    public Sucursal buscarSucursalPorID(Long id) {
        return sucursal.findById(id);
    }

    public Sucursal buscarSucursalPorNombre(String nombre) {
        return sucursal.findByNombreSucursal(nombre);
    }

    public Sucursal buscarSucursalPorId(Long id) {
        return sucursal.findById(id);
    }

}
