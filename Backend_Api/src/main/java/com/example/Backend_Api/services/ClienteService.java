package com.example.Backend_Api.services;

import com.example.Backend_Api.entities.Cliente;
import com.example.Backend_Api.repositories.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepositorio cliente_repositorio;

    // Registra un cliente
    public Cliente registrarCliente(long id_usu_registrado, String rut_registrado, String nombre_registrado, String direccion_registrado, int telefono_registrado, String correo_registrado, String contrasena_registrado, LocalDate fecha_nacimiento_cliente) {
        Cliente cliente = new Cliente();
        Cliente existentePorCorreo = cliente_repositorio.findByCorreo_registrado(cliente.getCorreo_registrado());
        if (existentePorCorreo != null) {
            return null;
        }
        Cliente existentePorRut = cliente_repositorio.findByRut_registrado(cliente.getRut_registrado());
        if (existentePorRut != null) {
            return null;
        }
        return cliente_repositorio.save(cliente);
    }

    public int login(String correo_registrado, String password_register) {
        Cliente cliente = cliente_repositorio.findByCorreo_registrado(correo_registrado);
        if (cliente != null) {
            if (password_register.equals(cliente.getPassword())) {
                return 1;
            }
        }
        return 0;
    }

    //metodo base que solo saca cosas, para que no aparezca como error
    public void registrarCliente(Cliente cliente) {
        registrarCliente(cliente.getId_usu_registrado(), cliente.getRut_registrado(),  cliente.getNombre_registrado(), cliente.getDireccion_registrado(), cliente.getTelefono_registrado(), cliente.getCorreo_registrado(), cliente.getPassword(), cliente.getFecha_nacimiento_cliente());
    }
}

