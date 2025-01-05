package com.example.backend.RowMappers;

import com.example.backend.Entity.Cliente;
import com.example.backend.Entity.Reserva;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.VehiculoReferencia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaRowMapperComplete implements RowMapper<Reserva> {

    @Override
    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Reserva reserva = new Reserva();
        // Mapeamos las columnas a las propiedades de la entidad
        reserva.setCostoTotal(rs.getDouble("costo_total"));
        reserva.setFechaInicioReserva(rs.getDate("fecha_inicio_reserva").toLocalDate());
        reserva.setFechaReserva(rs.getDate("fecha_reserva").toLocalDate());
        reserva.setFechaTerminoReserva(rs.getDate("fecha_termino_reserva").toLocalDate());
        reserva.setPagoReserva(rs.getBoolean("pago_reserva"));
        reserva.setReservaFinalizada(rs.getBoolean("reserva_finalizada"));
        reserva.setId_reserva(rs.getLong("id_reserva"));
        /*
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id_cliente"));
        reserva.setCliente(cliente);
        reserva.setId_reserva(rs.getLong("id_reserva"));
        Sucursal sucursalDevo = new Sucursal();
        Sucursal sucursalRetiro = new Sucursal();
        sucursalDevo.setIdSucursal(rs.getLong("id_sucursal_devolucion"));
        sucursalRetiro.setIdSucursal(rs.getLong("id_sucursal_retiro"));
        reserva.setSucursalDevolucion(sucursalDevo);
        reserva.setSucursalRetiro(sucursalRetiro);
        VehiculoReferencia vehiculoReferencia = new VehiculoReferencia();
        vehiculoReferencia.setModelo(rs.getString("modelo"));
        reserva.setVehiculoAsignado(vehiculoReferencia);
         */
        return reserva;
    }

}
