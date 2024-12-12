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

    @PostMapping("/login")
    public int loginCliente(@RequestBody Cliente clienteR) {
        return clienteService.login(clienteR.getEmail(), clienteR.getPassword());
    }

}

/*
ejemplo
{
    "nombre": "Juan Perez",
    "rut": "20722210-0",
    "telefono": "56996663811",
    "rol": "empleado",
    "email": "juan.perez@example.com",
    "password": "2lol12MN",
    "sucursal": { "nombreSucursal": "Sucursal uno"}
}
 */