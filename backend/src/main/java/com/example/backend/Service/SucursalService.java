package com.example.backend.Service;

import com.example.backend.Entity.Sucursal;
import com.example.backend.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {

    @Autowired
    SucursalRepository sucursal;

    Sucursal buscarSucursalPorID(Long id) {
        return sucursal.findById(id);
    }

    Sucursal buscarSucursalPorNombre(String nombre) {
        return sucursal.findByNombreSucursal(nombre);
    }

}
