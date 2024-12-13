package com.example.Backend_Api.services;

import com.example.Backend_Api.entities.Vehiculo;
import com.example.Backend_Api.repositories.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepositorio vehiculo_repositorio;

    public boolean registrarVehiculo(Vehiculo vehiculo){

        try {
            vehiculo_repositorio.save(vehiculo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Vehiculo obtenerVehiculoPorPlaca(String placa){
        return vehiculo_repositorio.findByPlaca_vehiculo(placa);
    }

    public List<Vehiculo> obtenerVehiculoPorMarca(String marca){
        return vehiculo_repositorio.findByMarca_vehiculo(marca);
    }

    public List<Vehiculo> obtenerVehiculoPorModelo(String modelo){
        return vehiculo_repositorio.findByModelo_vehiculo(modelo);
    }







}
