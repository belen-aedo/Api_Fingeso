package com.example.Backend_Api.controller;

import com.example.Backend_Api.entities.Cliente;
import com.example.Backend_Api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registrar")
    public Cliente registrarCliente(@RequestBody Cliente Nuevocliente) {
        return  clienteService.registrarCliente(
                Nuevocliente.getRut_registrado(),
                Nuevocliente.getNombre_registrado(),
                Nuevocliente.getDireccion_registrado(),
                Nuevocliente.getTelefono_registrado(),
                Nuevocliente.getCorreo_registrado(),
                Nuevocliente.getPassword(),
                Nuevocliente.getFecha_nacimiento_cliente());
    }

    @PostMapping("/login")
    public int loginCliente(@RequestBody Cliente clienteR) {
        return clienteService.login(clienteR.getCorreo_registrado(), clienteR.getPassword());
    }

    @GetMapping("/id")
    public Cliente ObtenerClientePorId(@RequestParam("id") String id) {
        System.out.printf("id por buscar:" + id);
        return clienteService.buscarClientePorId(Long.parseLong(id));
    }
}