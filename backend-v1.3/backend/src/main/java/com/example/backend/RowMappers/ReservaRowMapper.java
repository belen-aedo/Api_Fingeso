package com.example.backend.RowMappers;

import com.example.backend.Entity.Reserva;
import com.example.backend.Entity.Cliente;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Arriendo;
import com.example.backend.Entity.VehiculoReferencia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservaRowMapper implements RowMapper<Reserva> {

    // Constantes para los nombres de columnas
    private static final String ID_RESERVA = "id_reserva";
    private static final String CLIENTE = "cliente";  // Asume que este es un campo que representa el cliente
    private static final String SUCURSAL_RETIRO = "id_sucursal_retiro";
    private static final String SUCURSAL_DEVOLUCION = "id_sucursal_devolucion";
    private static final String ARRIENDO = "id_arriendo";
    private static final String VEHICULO_REFERENCIA = "id_vehiculo_referencia";
    private static final String COSTO_TOTAL = "costo_total";
    private static final String FECHA_INICIO_RESERVA = "fecha_inicio_reserva";
    private static final String FECHA_TERMINO_RESERVA = "fecha_termino_reserva";
    private static final String FECHA_RESERVA = "fecha_reserva";
    private static final String RESERVA_FINALIZADA = "reserva_finalizada";
    private static final String PAGO_RESERVA = "pago_reserva";

    @Override
    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reserva reserva = new Reserva();

        // Mapeamos las columnas a las propiedades de la entidad
        reserva.setId_reserva(rs.getLong(ID_RESERVA));

        // Asumimos que hay un mtodo para obtener el Cliente, Sucursal, Arriendo, y VehiculoReferencia
        // Podrías realizar consultas adicionales para obtener estos objetos si es necesario
        Cliente cliente = new Cliente(); // Necesitas obtener el Cliente de algún lado
        cliente.setIdCliente(rs.getLong(CLIENTE));
        reserva.setCliente(cliente);

        Sucursal sucursalRetiro = new Sucursal(); // Lo mismo para las sucursales
        sucursalRetiro.setIdSucursal(rs.getLong(SUCURSAL_RETIRO));
        reserva.setSucursalRetiro(sucursalRetiro);

        Sucursal sucursalDevolucion = new Sucursal();
        sucursalDevolucion.setIdSucursal(rs.getLong(SUCURSAL_DEVOLUCION));
        reserva.setSucursalDevolucion(sucursalDevolucion);

        Arriendo arriendo = new Arriendo(); // Lo mismo para Arriendo
        arriendo.setIdArriendo(rs.getLong(ARRIENDO));
        reserva.setArriendoAsignado(arriendo);

        VehiculoReferencia vehiculoReferencia = new VehiculoReferencia();
        vehiculoReferencia.setIdVehiculoReferencia(rs.getLong(VEHICULO_REFERENCIA));
        reserva.setVehiculoAsignado(vehiculoReferencia);

        // Mapeamos los campos simples
        reserva.setCostoTotal(rs.getDouble(COSTO_TOTAL));
        reserva.setFechaInicioReserva(rs.getObject(FECHA_INICIO_RESERVA, LocalDate.class));
        reserva.setFechaTerminoReserva(rs.getObject(FECHA_TERMINO_RESERVA, LocalDate.class));
        reserva.setFechaReserva(rs.getObject(FECHA_RESERVA, LocalDate.class));
        reserva.setReservaFinalizada(rs.getBoolean(RESERVA_FINALIZADA));
        reserva.setPagoReserva(rs.getBoolean(PAGO_RESERVA));

        return reserva;
    }
}