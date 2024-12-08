package com.example.Backend_Api.services;


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
    public Empleado registrarEmpleado(long idEmpleado, String nombreEmpleado, String rutEmpleado, Integer telefonoRegistrado, String rol, Sucursal sucursal, String correoRegistrado, String passwordRegister) {

        Empleado empleado = new Empleado(idEmpleado, nombreEmpleado, rutEmpleado, telefonoRegistrado, rol, sucursal, correoRegistrado, passwordRegister);

        LinkedList<String> RolesPermitidos = new LinkedList<String>();

        //Roles
        RolesPermitidos.add("gerente");
        RolesPermitidos.add("mecanico");
        RolesPermitidos.add("empleado");

        for(String rolPermitido : RolesPermitidos ) {
            if(rolPermitido.equals(empleado.getRol())){
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
        }
        return null; //Si no es el rol señalado
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





