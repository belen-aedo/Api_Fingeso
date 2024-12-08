package com.example.Backend_Api.services;

import com.example.Backend_Api.entities.Usuario_registrado;
import com.example.Backend_Api.repositories.Cliente_repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {
    @Autowired
    private Cliente_repositorio cliente_repositorio;

    // Registra un cliente
    public Usuario_registrado registrarCliente(long id_usu_registrado, String rut_registrado, String nombre_registrado, String apellido_registrado, String direccion_registrado, String telefono_registrado, String correo_registrado, LocalDate fecha_nacimiento_cliente) {
        Usuario_registrado cliente = new Usuario_registrado();
        Usuario_registrado existente = cliente_repositorio.findByCorreo_registrado(cliente.getCorreo_registrado());
        if (existente != null) {
            return null;
        }
        return cliente_repositorio.save(cliente);
    }

    public int login(String correo_registrado, String contrasena_registrado) {
        Usuario_registrado cliente = cliente_repositorio.findByCorreo_registrado(correo_registrado);
        if (cliente != null) {
            if (contrasena_registrado.equals(cliente.getContrasena_registrado())) {
                return 1; // Assuming 1 indicates a successful login
            }
        }
        return 0; // Assuming 0 indicates a failed login
    }
}