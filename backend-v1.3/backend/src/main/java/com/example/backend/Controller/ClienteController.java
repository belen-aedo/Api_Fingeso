package com.example.backend.Controller;

import com.example.backend.Entity.Cliente;
import com.example.backend.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class ClienteController {

    //url: http://localhost:8080/api/usuarios/

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/id")
    public Optional<Cliente> ObtenerClientePorId(@RequestParam("id_cliente") String id) {
        System.out.printf("id por buscar:" + id);
        return clienteService.buscarClientePorId(Long.parseLong(id));
    }


    @PostMapping("/registrar")
    public void registrarCliente(@RequestBody Cliente Nuevocliente) {
        try {
            clienteService.registrarCliente(
                    Nuevocliente.getRut(),
                    Nuevocliente.getNombre(),
                    Nuevocliente.getDireccion(),
                    Nuevocliente.getTelefono(),
                    Nuevocliente.getEmail(),
                    Nuevocliente.getPassword(),
                    Nuevocliente.getFechaNacimiento());
        } catch (Exception e) {
            System.out.println(e.getMessage() + " valor devuelto: " + e.getCause());
        }
    }


    @PostMapping("/login")
    public int loginCliente(@RequestBody Cliente clienteR) {
        return clienteService.login(clienteR.getEmail(), clienteR.getPassword());
    }

}
