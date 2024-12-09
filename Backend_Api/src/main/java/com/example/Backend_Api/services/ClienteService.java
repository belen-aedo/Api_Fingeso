package com.example.Backend_Api.services;

import com.example.Backend_Api.Utilidades.ValidacionDatos;
import com.example.Backend_Api.entities.Cliente;
import com.example.Backend_Api.repositories.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepositorio ClienteRepo;

    // Registra un cliente
    public Cliente registrarCliente(String RutRegistrado, String NombreRegistrado, String DireccionRegistrado, String TelefonoRegistrado, String CorreoRegistrado, String PasswordRegistrada, LocalDate FechaNacimientoCliente) {

        Cliente cliente = new Cliente(RutRegistrado, NombreRegistrado, DireccionRegistrado, TelefonoRegistrado, CorreoRegistrado, PasswordRegistrada, FechaNacimientoCliente);

        // Validar rut
        ValidacionDatos Validar = new ValidacionDatos(RutRegistrado);
        if (!Validar.validarRUT()) {
            throw new IllegalArgumentException("El RUT ingresado no es válido.");
        }

        // Validar correo
        Validar.setValidarDatoString(CorreoRegistrado);
        if(!Validar.validarCorreo()) {
            throw new IllegalArgumentException("El correo ingresado no es válido.");
        }

        // Validar contraseña
        Validar.setValidarDatoString(PasswordRegistrada);
        if(!Validar.validarPassword()) {
            throw new IllegalArgumentException("La contraseña ingresada no cumple con los requisitos de seguridad.");
        }

        // Validar Teléfono
        Validar.setValidarDatoString(TelefonoRegistrado);
        if (!Validar.validarNumero()) {
            throw new IllegalArgumentException("El número telefónico ingresado no es válido.");
        }

        // Validar Edad
        Validar.setValidarDatoFecha(FechaNacimientoCliente);
        if(!Validar.validarEdad()){
            throw new IllegalArgumentException("Debe ser mayor a 18 años");
        }

        Cliente existentePorCorreo = ClienteRepo.findByCorreo_registrado(cliente.getCorreo_registrado());
        Cliente existentePorRut = ClienteRepo.findByRut_registrado(cliente.getRut_registrado());

        if (existentePorCorreo != null) {
            return null;
        }

        if (existentePorRut != null) {
            return null;
        }
        return ClienteRepo.save(cliente);
    }

    public int login(String correo_registrado, String password_register) {
        Cliente cliente = ClienteRepo.findByCorreo_registrado(correo_registrado);
        if (cliente != null) {
            if (password_register.equals(cliente.getPassword())) {
                return 1;
            }
        }
        return 0;
    }

    public Cliente buscarClientePorId(long idClienteRegistrado) {
        return ClienteRepo.findById_registrado(idClienteRegistrado);
    }
}

