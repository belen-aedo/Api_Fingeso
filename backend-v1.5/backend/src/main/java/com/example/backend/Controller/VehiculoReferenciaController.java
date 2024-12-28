package com.example.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.backend.Entity.VehiculoReferencia;
import com.example.backend.Service.VehiculoReferenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculo-referencia")
public class VehiculoReferenciaController {

    @Autowired
    private VehiculoReferenciaService vehiculoReferenciaService;

    @PostMapping("/guardar")
    public ResponseEntity<VehiculoReferencia> guardarVehiculoReferencia(@RequestBody VehiculoReferencia vehiculoReferencia) {
        VehiculoReferencia savedVehiculo = vehiculoReferenciaService.saveVehiculoReferencia(vehiculoReferencia);
        return new ResponseEntity<>(savedVehiculo, HttpStatus.CREATED);
    }

    @PutMapping("/guardar-url/{id}")
    public ResponseEntity<VehiculoReferencia> guardarUrlForVehiculo(@PathVariable long id, @RequestBody String url) {
        VehiculoReferencia updatedVehiculo = vehiculoReferenciaService.saveUrlForVehiculo(id, url);
        return new ResponseEntity<>(updatedVehiculo, HttpStatus.OK);
    }
}

