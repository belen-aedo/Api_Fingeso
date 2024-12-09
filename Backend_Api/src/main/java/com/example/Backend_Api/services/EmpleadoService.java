package com.example.Backend_Api.services;


import com.example.Backend_Api.Utilidades.ValidacionDatos;
import com.example.Backend_Api.entities.Empleado;
import com.example.Backend_Api.entities.Sucursal;
import com.example.Backend_Api.repositories.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepositorio empleado_repositorio;

    // Registra un cliente
    public Empleado registrarEmpleado(long idEmpleado, String nombreEmpleado, String rutEmpleado, String telefonoRegistrado, String rol, Sucursal sucursal, String correoRegistrado, String passwordRegister) {
        Empleado empleado = new Empleado(idEmpleado, nombreEmpleado, rutEmpleado, telefonoRegistrado, rol, sucursal, correoRegistrado, passwordRegister);
        //Roles
        LinkedList<String> RolesPermitidos = new LinkedList<String>();
        RolesPermitidos.add("gerente");
        RolesPermitidos.add("mecanico");
        RolesPermitidos.add("empleado");

        // Verificar número telefónico válido
        ValidacionDatos Validar = new ValidacionDatos(telefonoRegistrado); // dato a validar contiene el número telefónico

        if (!Validar.validarNumero()) {
            throw new IllegalArgumentException("El número telefónico ingresado no es válido.");
        }

        // Verificar RUT
        Validar.setValidarDatoString(rutEmpleado); // cambiar el dato a validar, al RUT
        if (!Validar.validarRUT()) {
            throw new IllegalArgumentException("El RUT ingresado no es válido.");
        }

        // Verificar correo
        Validar.setValidarDatoString(correoRegistrado); // cambiar el dato a validar, al correo
        if (!Validar.validarCorreo()) {
            throw new IllegalArgumentException("El correo ingresado no es válido.");
        }

        // Verificar formato de contraseña
        Validar.setValidarDatoString(passwordRegister);
        if (!Validar.validarPassword()) {
            throw new IllegalArgumentException("La contraseña ingresada no cumple con los requisitos de seguridad.");
        }

        // Verificar existencia de sucursal
        if (!empleado_repositorio.sucursalExistente(sucursal)) {
            throw new IllegalArgumentException("La sucursal no está registrada.");
        }

        // Verificar si el rol escrito existe
        if (!RolesPermitidos.contains(rol)) {
            throw new IllegalArgumentException("El rol ingresado no es válido.");
        }

        Empleado existentePorCorreo = empleado_repositorio.findByCorreoElectronico(empleado.getCorreoElectronico());
        Empleado existentePorRut = empleado_repositorio.findByRut(empleado.getRut());
        if (existentePorCorreo != null) {
            return null;
        }
        if (existentePorRut != null) {
            return null;
        }

        return empleado_repositorio.save(empleado);
    }


    public int login(String correo_registrado, String password_register) {
        Empleado empleado = empleado_repositorio.findByCorreoElectronico(correo_registrado);
        if (empleado != null) {
            if (password_register.equals(empleado.getPassword())) {
                return 1;
            }
        }
        return 0;
    }
}





