package com.example.backend.Controller;

import com.example.backend.Entity.*;
import com.example.backend.Repository.SucursalRepository;
import com.example.backend.Repository.VehiculoReferenciaRepository;
import com.example.backend.Service.*;
import com.example.backend.Utilidades.ValidacionDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/login")
    public int loginCliente(@RequestBody Cliente clienteR) {
        return clienteService.login(clienteR.getEmail(), clienteR.getPassword());
    }

    // Paso 2-3-4-5
    // Obtener vehículos disponibles
    @GetMapping("/getAvaliableVehiculos")
    public List<VehiculoReferencia> getAllVehiculosAvailable(
            @RequestParam("nombreSucursalR") String nombreSucursalRet,
            @RequestParam("fechaRetiro") LocalDate fechaRetiro,
            @RequestParam("fechaDevolucion") LocalDate fechaDevolucion,
            @RequestParam("nombreSucursalD") String nombreSucursalDevo){

        try {
            List<Vehiculo> VehiculoDispo = vehiculoFilterService.ObtenerVehiculosDisponibles(fechaRetiro, fechaDevolucion,nombreSucursalRet) ;
            List<VehiculoReferencia> VehiculoReferencias = new LinkedList<>();
            VehiculoReferencias = vehiculoFilterService.obtenerVehiculosReferencia(VehiculoDispo);

            return VehiculoReferencias;
        } catch (Exception e) {
            System.out.println("Error al obtener los vehículos disponibles: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Seleccionar Vehículo
    @GetMapping("/SelecionarVehiculo")
    public Vehiculo SelecionarVehiculo(@RequestParam("modelo") String modelo,
                                       @RequestParam("nombreSucursalR") String nombreSucursalRet,
                                       @RequestParam("fechaRetiro") LocalDate fechaRetiro,
                                       @RequestParam("fechaDevolucion") LocalDate fechaDevolucion,
                                       @RequestParam("nombreSucursalD") String nombreSucursalDevo) {
        // vehículos disponibles
        List<Vehiculo> VehiculoDispo = vehiculoFilterService.ObtenerVehiculosDisponibles(fechaRetiro, fechaDevolucion,nombreSucursalRet);
        List<Vehiculo> VehiculosMismoModelo = new LinkedList<>();
        for (Vehiculo v : VehiculoDispo) {
            if (v.getModelo().equals(modelo)) {
                VehiculosMismoModelo.add(v);
            }
        }
        // tomo el primer vehículo
        return VehiculosMismoModelo.get(0);
    }

    // Proceso de pago
    // Confirmar reserva con vehiculo seleccionado

    @GetMapping("/VehiculoSelecionado")
    public String RealizarAgendamiento(@RequestParam("Patente") String Patente,
                                @RequestParam("nombreSucursalR") String nombreSucursalRet,
                                @RequestParam("fechaRetiro") LocalDate fechaRetiro,
                                @RequestParam("fechaDevolucion") LocalDate fechaDevolucion,
                                @RequestParam("nombreSucursalD") String nombreSucursalDevo,
                                       @RequestParam("Rut") String rut) {

        try {
            Vehiculo AgendarVehiculo = vehiculoFilterService.BuscarVehiculoPorPatente(Patente);
            Sucursal AgendarSucursalRetiro = sucursalService.buscarSucursalPorNombre(nombreSucursalRet);
            Sucursal AgensarSucursalDevo = sucursalService.buscarSucursalPorNombre(nombreSucursalDevo);
            Cliente cliente = clienteService.buscarClientePorRut(rut);
            VehiculoReferencia VehiculoReferencia = vehiculoFilterService.obtenerReferencia(AgendarVehiculo.getModelo());
            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setVehiculoAsignado(VehiculoReferencia);
            reserva.setFechaReserva(LocalDate.now());
            reserva.setFechaTerminoReserva(fechaDevolucion);
            reserva.setFechaInicioReserva(fechaRetiro);
            reserva.setSucursalRetiro(AgendarSucursalRetiro);
            reserva.setSucursalDevolucion(AgensarSucursalDevo);
            reserva.setReservaFinalizada(Boolean.FALSE);
            reserva.setCostoTotal(Double.valueOf(0));// de momento es 0 devido a que no se esta realizando nigun
            reserva.setPagoReserva(Boolean.TRUE); // SE SIMULA QUE SE PAGUE LA RESERVA

            //Guardar reserva
            reservaService.GuardarReserva(reserva);
            Long IdReserva = reservaService.BuscarReservaPorIdCliente(cliente.getIdCliente());
            reserva.setId_reserva(IdReserva);

            //crear aegndamiento
            ValidacionDatos ValidacionDatos = new ValidacionDatos();
            LocalDate NuevFechaDispo = ValidacionDatos.calcularNuevaFechaFin(fechaRetiro, fechaDevolucion);

            agendarRerservaService.realizarAgendamiento(AgendarVehiculo, AgendarSucursalRetiro, AgensarSucursalDevo, fechaRetiro, fechaDevolucion, NuevFechaDispo, reserva, cliente);

            return "Agendamiento Registrado exitosamente";

        } catch (Exception e) {

            return "Error al realizar agendamiento " + e.getMessage();
        }
    }




    // Paso 6 selección del vehiculo
    // Paso 7
    // Paso 8 confirma vehiculo
    // Paso 11 confirmar datos de reserva
    // Paso 12-13 precio total a pagar
    // Pagar
    // Mostrar que la reserva de a realizado-guardar en la bases de datos




}
