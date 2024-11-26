package com.example.Backend_Api.controller;


import com.example.Backend_Api.entities.Vehiculo;
import com.example.Backend_Api.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/registrar")
    public boolean registrarVehiculo(@RequestBody Vehiculo vehiculo){
        return vehiculoService.registrarVehiculo(vehiculo);
    }


}
