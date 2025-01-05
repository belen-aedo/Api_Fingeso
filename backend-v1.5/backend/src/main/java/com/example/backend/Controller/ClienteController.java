package com.example.backend.Controller;

import com.example.backend.Entity.*;
import com.example.backend.Service.*;
import com.example.backend.Utilidades.ValidacionDatos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Primero registrarse
    @PostMapping("/registrar")
    public String registrarCliente(@RequestBody Cliente Nuevocliente) {
        try {
            clienteService.registrarCliente(
                    Nuevocliente.getRut(),
                    Nuevocliente.getNombre(),
                    Nuevocliente.getDireccion(),
                    Nuevocliente.getTelefono(),
                    Nuevocliente.getEmail(),
                    Nuevocliente.getPassword(),
                    Nuevocliente.getFechaNacimiento());
            return "Cliente registrado exitosamente";
        } catch (Exception e) {
            return "Error en registrarse "  + e.getMessage();
        }
    }

    // Loguearse
    @PostMapping("/login")
    public int loginCliente(@RequestBody Cliente clienteR) {
        return clienteService.login(clienteR.getEmail(), clienteR.getPassword());
    }

    // reservas del mismo cliente
    @GetMapping("/Reservas")
    public List<Reserva> ObtenerReservasCliente(@RequestParam String correoCliente) {
        try {
            return clienteService.buscarReservarPorCliente(correoCliente);
        }catch (Exception e) {
            System.out.println("Error en obtenerReservasCliente " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
