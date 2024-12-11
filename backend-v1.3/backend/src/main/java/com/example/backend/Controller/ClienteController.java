package com.example.backend.Controller;

import com.example.backend.Entity.Cliente;
import com.example.backend.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/id")
    public Cliente ObtenerClientePorId(@RequestParam("id") String id) {
        System.out.printf("id por buscar:" + id);
        return clienteService.buscarClientePorId(Long.parseLong(id));
    }

    @PostMapping("/registrar")
    public Cliente registrarCliente(@RequestBody Cliente Nuevocliente) {
        return  clienteService.registrarCliente(
                Nuevocliente.getRut(),
                Nuevocliente.getNombre(),
                Nuevocliente.getDireccion(),
                Nuevocliente.getTelefono(),
                Nuevocliente.getEmail(),
                Nuevocliente.getPassword(),
                Nuevocliente.getFechaNacimiento());
    }

    @PostMapping("/login")
    public int loginCliente(@RequestBody Cliente clienteR) {
        return clienteService.login(clienteR.getEmail(), clienteR.getPassword());
    }
}
