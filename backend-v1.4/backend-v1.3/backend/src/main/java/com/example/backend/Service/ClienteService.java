package com.example.backend.Service;

import com.example.backend.Entity.Cliente;
import com.example.backend.Repository.ClienteRepository;
import com.example.backend.Utilidades.ValidacionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    /**
     * Registrar cliente en la pagína
     * @param RutRegistrado Rut a del cliente
     * @param NombreRegistrado Nombre
     * @param DireccionRegistrado direccion
     * @param TelefonoRegistrado telefono movil
     * @param CorreoRegistrado correo del cliente
     * @param PasswordRegistrada contraseña a guardar
     * @param FechaNacimientoCliente fecha de nacimiento
     * @return un cliente registrado
     */
    public void registrarCliente(String RutRegistrado, String NombreRegistrado, String DireccionRegistrado, String TelefonoRegistrado, String CorreoRegistrado, String PasswordRegistrada, LocalDate FechaNacimientoCliente) {

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

        Cliente existentePorCorreo = clienteRepo.findByEmail(cliente.getEmail());
        Cliente existentePorRut = clienteRepo.findByRut(cliente.getRut());

        if (existentePorCorreo != null) {
            throw new IllegalArgumentException("ya se encuentra registrado con el correo electronico: "+ cliente.getEmail());
        }

        if (existentePorRut != null) {
            throw new IllegalArgumentException("ya se encuentra registrado.");
        }
        System.out.println("registrado(a) correctamente:" + cliente.getNombre());

        clienteRepo.save(cliente);
    }

    /**
     * Logear cliente en la pagina
     * @param correo_registrado
     * @param password_register
     * @return valida el login para acceder a su cuenta
     */
    public int login(String correo_registrado, String password_register) {
        Cliente cliente = clienteRepo.findByEmail(correo_registrado);
        if (cliente != null) {
            if (password_register.equals(cliente.getPassword())) {
                System.out.println("bienvenido(a) devuelta " + cliente.getNombre());
                return 1;
            }
        }
        return 0;
    }

    public Optional<Cliente> buscarClientePorId(long idClienteRegistrado) {
        return clienteRepo.findById(idClienteRegistrado);
    }

    public Cliente buscarClientePorRut(String rut) {
        return clienteRepo.findByRut(rut);
    }

    public Cliente buscarClientePorCorreo(String email) {
        return clienteRepo.findByEmail(email);
    }

}

