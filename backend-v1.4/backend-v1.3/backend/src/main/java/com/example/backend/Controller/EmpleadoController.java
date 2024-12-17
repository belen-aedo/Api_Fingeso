package com.example.backend.Controller;

import com.example.backend.Entity.Arriendo;
import com.example.backend.Entity.Empleado;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
import com.example.backend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/empleado")
@CrossOrigin
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    SucursalService sucursalService;

    @Autowired
    VehiculoFilterService vehiculoFilterService;

    @Autowired
    AgendarRerserva agendarRerservaService;

    @Autowired
    ArriendoService arriendoService;

    @PostMapping("/registrar")
    public String registrarEmpleado(@RequestBody Empleado nuevoEmpleado) {
        try {
            empleadoService.registrarEmpleado(
                    nuevoEmpleado.getNombre(),
                    nuevoEmpleado.getRut(),
                    nuevoEmpleado.getTelefono(),
                    nuevoEmpleado.getRol(),
                    nuevoEmpleado.getSucursal().getNombreSucursal(),
                    nuevoEmpleado.getEmail(),
                    nuevoEmpleado.getPassword()
            );
            return "Empleado registrado con exitosamente.";
        } catch (Exception e) {
            return "Error al registrar empleado: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public int loginEmpleado(@RequestBody Empleado empleadoR) {
            return empleadoService.login(empleadoR.getEmail(), empleadoR.getPassword());
    }


    @PostMapping("/DatosArriendo")
    public String ObtenerDatosArriendo(@RequestParam("Nombre Sucursal") String sucursalActual, @RequestParam("id reserva") Long idReserva) {
        StringBuilder sb = new StringBuilder();
        try {
            Sucursal sucursal = sucursalService.buscarSucursalPorNombre(sucursalActual);
            Vehiculo vehiculo = vehiculoFilterService.ObtenerDatosDeVehiculos(idReserva);
            Arriendo arriendo = empleadoService.BuscarArriendoPorReserva(idReserva);
            if (arriendo.getEstadoArriendo()) {
                sb.append("El arriendo continua");
                if (!arriendo.getSucursalDevolucion().getNombreSucursal().equals(sucursal)) {
                    sb.append("|");
                    sb.append("El arriendo no es de esta sucursal");
                }
                if (arriendo.getFechaTerminoArriendo().isEqual(LocalDate.now()) || arriendo.getFechaTerminoArriendo().isBefore(LocalDate.now())) {
                    //arriendo en cuanto a fecha es vigente
                    sb.append("|");
                    sb.append("El arriendo:" + " " + arriendo.toString() + " " + "Esta vingente");
                    return String.valueOf(sb);
                }
                sb.append("Vehiculo asociado al arriendo: \n").append("Vehiculo ").append("\n");
                sb.append(String.valueOf(vehiculo.getId()) + "\n" + "Patente: " + vehiculo.getPatente() + "\n"
                        + "Marca: " + vehiculo.getMarca() + "\n"
                        + "Modelo: " + vehiculo.getModelo() + "\n"
                        + "Color: " + vehiculo.getColorPrincipal() + "\n"
                        + "Año: " + String.valueOf(vehiculo.getYear()) + "\n"
                        + "EstadoVehiculo: " + vehiculo.getEstadoVehiculo() + "\n"
                        + "Kilomtraje: " + String.valueOf(vehiculo.getKilometrajeVehiculo()) + "\n"
                        + "Ubicacion: " + String.valueOf(vehiculo.getUbicacionActual()) + "\n");
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    @PostMapping("/confirmaDevolucion")
    public String confirmarDevolucion(@RequestParam("EstadoPendiente") int Bool, @RequestParam("idReserva") Long idReserva){
        try{
            // se borra el agendamiento del vehículo
        agendarRerservaService.BorrarAgendamiento(idReserva);
        Arriendo arriendo = empleadoService.BuscarArriendoPorReserva(idReserva);

        // si el vehículo no se encontró en buenas condiciones queda el arriendo como pendiente
        if(Bool==1){
            arriendoService.CambiarEstadoPendiente(arriendo.getId_arriendo(),true );
        }

        // el arriendo concluye
        arriendoService.CambiarEstadoPendiente(arriendo.getId_arriendo(),false );

            return "devolución confirmada";

        }catch (Exception e){
            return "Error al confirmar el devolución: " + e.getMessage();
        }

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