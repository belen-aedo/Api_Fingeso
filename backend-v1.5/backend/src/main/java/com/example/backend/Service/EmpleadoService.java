package com.example.backend.Service;

import com.example.backend.Entity.Arriendo;
import com.example.backend.Entity.Empleado;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Repository.*;
import com.example.backend.Utilidades.ValidacionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ArriendoRepository arriendoRepository;


    // Registra un cliente
    public void registrarEmpleado(String nombreEmpleado, String rutEmpleado, String telefonoRegistrado, String rol, String nombreSucursal, String correoRegistrado, String passwordRegister) {

        Sucursal sucursal = sucursalRepository.findByNombreSucursal(nombreSucursal);

        if (sucursal == null) {
            throw new IllegalArgumentException("La sucursal indicada no existe.");
        }

        Empleado empleado = new Empleado(nombreEmpleado, rutEmpleado, telefonoRegistrado, rol, sucursal, correoRegistrado, passwordRegister);

        //Roles
        LinkedList<String> RolesPermitidos = new LinkedList<String>();
        RolesPermitidos.add("gerente");
        RolesPermitidos.add("mecanico");
        RolesPermitidos.add("asalariado");

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

        // Verificar si el rol escrito existe
        if (!RolesPermitidos.contains(rol)) {
            throw new IllegalArgumentException("El rol ingresado no es válido.");
        }

        Empleado existentePorCorreo = empleadoRepository.findByEmail(empleado.getEmail());
        Empleado existentePorRut = empleadoRepository.findByRut(empleado.getRut());

        if (existentePorCorreo != null) {
            throw new IllegalArgumentException("\n ya se encuentra registrado con el correo electronico: " + existentePorCorreo.getEmail());
        }
        if (existentePorRut != null) {
            throw new IllegalArgumentException("\n ya se encuentra registrado con el rut: " + existentePorRut.getRut());
        }

        empleadoRepository.save(empleado);
    }


    public String login(String correo_registrado, String password_register) {
        Empleado empleado = empleadoRepository.findByEmail(correo_registrado);
        if (empleado != null) {
            if (password_register.equals(empleado.getPassword())) {
                return empleado.getRol();
            }
        }
        return "no existe";
    }


    public Arriendo BuscarArriendoPorReserva(Long id_reserva) {

        Arriendo arriendo = arriendoRepository.findArriendoByIdReserva(id_reserva);
        if (arriendo == null) {
            throw new IllegalArgumentException("No existe el arriendo vinculado al ID de la reserva indicada: " + id_reserva);
        }
        return arriendo;
    }

}
