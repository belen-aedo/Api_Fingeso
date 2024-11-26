package com.example.Backend_Api.services;

import com.example.Backend_Api.entities.Vehiculo;
import com.example.Backend_Api.repositories.Vehiculo_repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    @Autowired
    private Vehiculo_repositorio vehiculo_repositorio;

    public boolean registrarVehiculo(Vehiculo vehiculo){
        try {
            vehiculo_repositorio.save(vehiculo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
