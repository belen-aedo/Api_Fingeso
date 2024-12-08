package com.example.Backend_Api.controller;

import com.example.Backend_Api.entities.Cliente;
import com.example.Backend_Api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public void registrarCliente(@RequestBody Cliente cliente) {
        clienteService.registrarCliente(cliente);
    }
}