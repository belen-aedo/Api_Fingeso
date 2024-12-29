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
    public String loginEmpleado(@RequestBody Empleado empleadoR) {
            return empleadoService.login(empleadoR.getEmail(), empleadoR.getPassword());
    }


    //ejemplo http://localhost:8080/api/empleado/DatosArriendo?Nombre Sucursal=Sucursal Central&id reserva=1&rol=gerente
    @GetMapping("/DatosArriendo")
    public String ObtenerDatosArriendo(@RequestParam("Nombre Sucursal") String sucursalActual, @RequestParam("id reserva") Long idReserva, @RequestParam("rol") String rol) {

        StringBuilder sb = new StringBuilder();
        try {

            if(rol.equals("gerente") || rol.equals("asalariado")) {

            Sucursal sucursal = sucursalService.buscarSucursalPorNombre(sucursalActual);
            Vehiculo vehiculo = vehiculoFilterService.ObtenerDatosDeVehiculos(idReserva); // busco el vehiculo vinculado al arriendo por el id de la reserva
            Arriendo arriendo = empleadoService.BuscarArriendoPorReserva(idReserva); // busco el arriendo vinculado a la reserva
            Sucursal sucursal2 = sucursalService.buscarSucursalPorId(arriendo.getSucursalDevolucion().getIdSucursal()); // busco la sucursal de devolución vinculada al arriendo
            if (arriendo.getEstadoArriendo()) {

                sb.append("El arriendo continua");

                // evaluó si la sucursal en la que se está, es la sucursal correspondiente de devolución
                if (!sucursal2.getNombreSucursal().equals(sucursal.getNombreSucursal())) {
                    sb.append("\n");
                    sb.append("El arriendo no es de esta sucursal\n");
                    sb.append("Sucursal actual: ").append(sucursalActual).append(" \nSucursal de devolución del arriendo: ").append(sucursal2.getNombreSucursal());
                    sb.append("\n");

                }else {
                    sb.append("\n");
                    sb.append("El arriendo pertenece a esta sucursal\n");
                    sb.append("Sucursal actual: ").append(sucursalActual).append(" \nSucursal de devolución del arriendo: ").append(sucursal2.getNombreSucursal());
                    sb.append("\n");
                }
                // evaluó si las fechas corresponden
                if ( arriendo.getFechaInicioArriendo().isAfter(LocalDate.now())) {
                    sb.append("\n");
                    sb.append("El arriendo todavía no comienza");
                    sb.append("\nfecha actual :").append(LocalDate.now()).append("\nfecha inicio del arriendo: ").append(arriendo.getFechaInicioArriendo());
                }else if (arriendo.getFechaTerminoArriendo().isEqual(LocalDate.now()) || arriendo.getFechaTerminoArriendo().isAfter(LocalDate.now())) {
                    sb.append("\n");
                    sb.append("Arriendo dentro del plazo establecido");
                    sb.append("\nfecha actual :").append(LocalDate.now()).append("\nfecha de termino del arriendo: ").append(arriendo.getFechaTerminoArriendo());
                }else {
                    sb.append("\n");
                    sb.append("Arriendo fuera del plazo establecido");
                    sb.append("\nfecha actual :").append(LocalDate.now()).append("\nfecha de termino del arriendo: ").append(arriendo.getFechaTerminoArriendo());
                }
                // datos del vehículo asociado al arriendo
                sb.append("\n\nVehiculo asociado al arriendo: \n");
                sb.append("ID: ").append(vehiculo.getId()).append("\n").append("Patente: ").append(vehiculo.getPatente()).append("\n").append("Marca: ").append(vehiculo.getMarca()).append("\n").append("Modelo: ").append(vehiculo.getModelo()).append("\n").append("Color principal: ").append(vehiculo.getColorPrincipal()).append("\n");

                // persona asociada al arriendo
                sb.append("\nRut persona asociad al arriendo: ");
                sb.append(arriendo.getRutCliente());

                return String.valueOf(sb);
            }else {
                sb.append("Arriendo concluido");
            }

            }else {
                return "Su rol no tiene los permisos para revisar la información solicitada";
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    // ejemplo http://localhost:8080/api/empleado/confirmaDevolucion?EstadoPendiente=1&idReserva=4&rol=asalariado
    @PostMapping("/confirmaDevolucion")
    public String confirmarDevolucion(@RequestParam("EstadoPendiente") int Bool, @RequestParam("idReserva") Long idReserva, @RequestParam("rol") String rol){

        StringBuilder sb = new StringBuilder();
        try{
            if(rol.equals("gerente") || rol.equals("asalariado")) {
            // se borra el agendamiento del vehículo
        agendarRerservaService.BorrarAgendamiento(idReserva);
        Arriendo arriendo = empleadoService.BuscarArriendoPorReserva(idReserva);

        // si el vehículo no se encontró en buenas condiciones queda el arriendo como pendiente
        if(Bool==1){
            arriendoService.CambiarEstadoPendiente(arriendo.getId_arriendo(),true );
            sb.append("Arriendo marcado como pendiente con el ID: ").append(arriendo.getId_arriendo()).append("\n");
        }

        // el arriendo concluye
        arriendoService.CambiarEstado(arriendo.getId_arriendo(),false );
        sb.append("Devolución confirmada");

            return sb.toString();

            }else {
                return "Su rol no tiene los permisos para ejecutar la acción solicitada";
            }

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