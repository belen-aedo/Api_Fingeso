package com.example.backend.Controller;

import com.example.backend.Entity.*;
import com.example.backend.Service.*;
import com.example.backend.Utilidades.ValidacionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin

public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    VehiculoFilterService vehiculoFilterService;

    @Autowired
    SucursalService sucursalService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    AgendarRerserva agendarRerservaService;

    @GetMapping("/id")
    public Optional<Cliente> ObtenerClientePorId(@RequestParam("id_cliente") String id) {
        System.out.printf("id por buscar:" + id);
        return clienteService.buscarClientePorId(Long.parseLong(id));
    }

    // Primero registrarse
    @PostMapping("/registrar")
    public String registrarCliente(@RequestBody Cliente Nuevocliente) {
        try {
            clienteService.registrarCliente(
                    Nuevocliente.getRut(),
                    Nuevocliente.getNombre(),
                    Nuevocliente.getDireccion(),
                    Nuevocliente.getTelefono(),
                    Nuevocliente.getEmail(),
                    Nuevocliente.getPassword(),
                    Nuevocliente.getFechaNacimiento());
            return "Cliente registrado exitosamente";
        } catch (Exception e) {
            return "Error en registrarse "  + e.getMessage();
        }
    }

    // Loguearse
    @PostMapping("/login")
    public int loginCliente(@RequestBody Cliente clienteR) {
        return clienteService.login(clienteR.getEmail(), clienteR.getPassword());
    }


    // Obtener vehículos disponibles obteniendo los vehículos referencia
    @GetMapping("/ObtenerVehiculoDisponibles")
    public List<VehiculoReferencia> getAllVehiculosAvailable(
            @RequestParam("nombreSucursalR") String nombreSucursalRet,
            @RequestParam("fechaRetiro") LocalDate fechaRetiro,
            @RequestParam("fechaDevolucion") LocalDate fechaDevolucion,
            @RequestParam("nombreSucursalD") String nombreSucursalDevo){

        try {
            List<Vehiculo> VehiculoDispo = vehiculoFilterService.ObtenerVehiculosDisponibles(fechaRetiro, fechaDevolucion,nombreSucursalRet) ;
            List<VehiculoReferencia> VehiculoReferencias = new LinkedList<>();
            return vehiculoFilterService.obtenerVehiculosReferencia(VehiculoDispo);
            // la idea es que al seleccionar un vehiculo la llave del vehiculo seleccionado se guarde
        } catch (Exception e) {

            System.out.println("Error al obtener los vehículos disponibles: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Seleccionar el vehículo referencia para ver los detalles con el costo de arriendo y devolución cambiados
    @PostMapping("/VerDetallesVehiculo")
    public VehiculoReferencia obtenerDetalles(
            @RequestBody VehiculoReferencia vehiculoReferencia,
            @RequestParam("fechaRetiro") LocalDate fechaRetiro,
            @RequestParam("fechaDevolucion") LocalDate fechaDevolucion) {

        try {
            // Calcular costos y agregarlos a la lista
            long dias = ChronoUnit.DAYS.between(fechaRetiro, fechaDevolucion);
            vehiculoReferencia.setCostoReservaVehiculo(vehiculoReferencia.getCostoReservaVehiculo() * dias);
            vehiculoReferencia.setCostoArriendoVehiculo(vehiculoReferencia.getCostoArriendoVehiculo() * dias);

        } catch (Exception e) {
            return new VehiculoReferencia();
        }
        return vehiculoReferencia;
        // la idea es guardar el vehiculo referencia ya que contiene los costos de la reserva, por lo cual se debe en este punto actualiza
        // la llave que contiene el vehiculo referencia guardado anteriormente
    }

    // Confirmar vehiculo obteniendo el dato del vehículo físico disponible
    @PostMapping("/SelecionarVehiculoReferencia")
    public String SelecionarVehiculo(@RequestBody VehiculoReferencia vehiculoReferencia,
                                       @RequestParam("nombreSucursalR") String nombreSucursalRet,
                                       @RequestParam("fechaRetiro") LocalDate fechaRetiro,
                                       @RequestParam("fechaDevolucion") LocalDate fechaDevolucion,
                                       @RequestParam("nombreSucursalD") String nombreSucursalDevo) {
        // vehículos disponibles
        List<Vehiculo> VehiculoDispo = vehiculoFilterService.ObtenerVehiculosDisponibles(fechaRetiro, fechaDevolucion,nombreSucursalRet);
        List<Vehiculo> VehiculosMismoModelo = new LinkedList<>();
        for (Vehiculo v : VehiculoDispo) {
            if (v.getModelo().equals(vehiculoReferencia.getModelo())) {
                VehiculosMismoModelo.add(v);
            }
        }
        // tomo el primer vehículo físico que esté disponible del mismo modelo, la idea es guardar este dato en alguna llave
        return VehiculosMismoModelo.get(0).getPatente();
    }

    // Mostrar detalles de la reserva // siempre se trabaja con el mismo vehiculo referencia que esta en la llave
    @PostMapping("/DetallesReserva")
    public List<String> obtenerDetallesReserva(@RequestBody VehiculoReferencia vehiculoReferencia,
                                                                        @RequestParam("nombreSucursalR") String nombreSucursalRet,
                                                                        @RequestParam("fechaRetiro") LocalDate fechaRetiro,
                                                                        @RequestParam("fechaDevolucion") LocalDate fechaDevolucion,
                                                                        @RequestParam("nombreSucursalD") String nombreSucursalDevo){
        List<String> DetallesReserva = new LinkedList<>();
        DetallesReserva.add("Costo Reserva: " + vehiculoReferencia.getCostoReservaVehiculo());
        DetallesReserva.add("Sucursal de retiro: " + nombreSucursalRet);
        DetallesReserva.add("Sucursal de devolución: " + nombreSucursalDevo);
        DetallesReserva.add("Fecha de retiro: " + fechaRetiro);
        DetallesReserva.add("Fecha de devolución: " + fechaDevolucion);
        DetallesReserva.add("Modelo: " + vehiculoReferencia.getModelo());
        DetallesReserva.add(vehiculoReferencia.getDescripcionPublicacion());
        DetallesReserva.add("Url:" + vehiculoReferencia.getUrl());
        return DetallesReserva;
    }

    // Se omite el pago, se hace como que esta pagado
    // Confirmar reserva con vehiculo seleccionado
    @PostMapping("/ConfirmarReserva")
    public String RealizarAgendamiento(
                                       @RequestBody VehiculoReferencia vehiculoReferencia,
                                       @RequestParam("patente") String patente,
                                       @RequestParam("nombreSucursalR") String nombreSucursalRet,
                                       @RequestParam("fechaRetiro") LocalDate fechaRetiro,
                                       @RequestParam("fechaDevolucion") LocalDate fechaDevolucion,
                                       @RequestParam("nombreSucursalD") String nombreSucursalDevo,
                                       @RequestParam("email") String email) {

        Reserva reserva = null;

        try {
            Sucursal AgendarSucursalRetiro = sucursalService.buscarSucursalPorNombre(nombreSucursalRet);
            Sucursal AgensarSucursalDevo = sucursalService.buscarSucursalPorNombre(nombreSucursalDevo);
            Vehiculo vehiculo = vehiculoFilterService.BuscarVehiculoPorPatente(patente);
            Cliente cliente = clienteService.buscarClientePorCorreo(email);

            reserva = new Reserva();

            reserva.setCliente(cliente);
            reserva.setVehiculoAsignado(vehiculoReferencia);
            reserva.setFechaReserva(LocalDate.now());
            reserva.setFechaTerminoReserva(fechaDevolucion);
            reserva.setFechaInicioReserva(fechaRetiro);
            reserva.setSucursalRetiro(AgendarSucursalRetiro);
            reserva.setSucursalDevolucion(AgensarSucursalDevo);
            reserva.setReservaFinalizada(Boolean.FALSE);
            reserva.setCostoTotal(vehiculoReferencia.getCostoReservaVehiculo());// costo que se cambio anteriormente
            reserva.setPagoReserva(Boolean.TRUE); // SE SIMULA QUE SE PAGUE LA RESERVA

            //Guardar reserva // funciona bien
            reservaService.GuardarReserva(reserva);
            Long IdReserva = reservaService.BuscarReservaPorIdCliente(cliente.getIdCliente());
            reserva.setId_reserva(IdReserva);

            //crear agendamiento // con problemas
            ValidacionDatos ValidacionDatos = new ValidacionDatos();
            LocalDate NuevFechaDispo = ValidacionDatos.calcularNuevaFechaFin(fechaRetiro, fechaDevolucion);

            agendarRerservaService.realizarAgendamiento(vehiculo, AgendarSucursalRetiro, AgensarSucursalDevo, fechaRetiro, fechaDevolucion, NuevFechaDispo, reserva, cliente);

            return "Agendamiento Registrado exitosamente";

        } catch (Exception e) {

            reservaService.borrarPorId(reserva.getId_reserva());

            return "Error al realizar agendamiento " + e.getMessage();
        }
    }

}
