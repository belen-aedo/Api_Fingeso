package com.example.backend.Controller;

import com.example.backend.Entity.Vehiculo;
import com.example.backend.Service.VehiculoFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculo")
@CrossOrigin
public class VehiculoFilterController {

    @Autowired
    VehiculoFilterService vehiculoFilterService;

    //ejemplo http://localhost:8080/api/vehiculo/getByPatente?patente=ABC123

    @GetMapping("/getByPatente")
    public Vehiculo getVehiculoByPatente(@RequestParam("patente") String patente) {
        try{
        return vehiculoFilterService.BuscarVehiculoPorPatente(patente);
        }catch(Exception e){
            System.out.println("Error al obtener el vehiculo " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/getBySucursal")
    public List<Vehiculo> getAllVehiculosBySucursal(@RequestParam("nombreSucursal") String nombreSucursal) {
        try{
            return vehiculoFilterService.ObtenerVehiculosPorSucursal(nombreSucursal);
        }catch(Exception e){
            System.out.println("Error al obtener vehiculos " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping("/getAvaliableVehiculos")
    public List<Vehiculo> getAllVehiculosAvailable(
            @RequestParam("nombreSucursal") String nombreSucursal,
            @RequestParam("fechaRetiro") LocalDate fechaRetiro ) {
        // Implementa
        try {
            // Llamar al servicio para obtener los vehículos disponibles
            return vehiculoFilterService.ObtenerVehiculosDisponibles(nombreSucursal, fechaRetiro);
        } catch (Exception e) {
            System.out.println("Error al obtener los vehículos disponibles: " + e.getMessage());
            return new ArrayList<>();
        }

    }
}