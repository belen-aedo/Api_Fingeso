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

        //Verificar número telefónico valido
        ValidacionDatos Validar = new ValidacionDatos(telefonoRegistrado); //dato a validar contiene el numero telefónico
        if (!Validar.validarNumero()){
            return null;
        }

        //Verificar rut
        Validar.setValidarDato(rutEmpleado);// cambiar el dato a validar, al rut

        if (!Validar.validarRUT()){
           return null;
        }

        //Verificar correo
        Validar.setValidarDato(correoRegistrado);// cambiar el dato a validar, al correo
        if (!Validar.validarCorreo()){
            return null;
        }

        //Verificar existencia de sucursal
        if (!empleado_repositorio.sucursalExistente(sucursal)) {
            throw new IllegalArgumentException("Sucursal no registrada");
        }

        //Verificar si el rol escrito existe
        for(String rolPermitido : RolesPermitidos ) {
            if(rolPermitido.equals(empleado.getRol())){

                //Verificado el rol se comprueba si ya ha sido registrado por correo y por rut
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





