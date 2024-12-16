package com.example.backend.RowMappers;

import com.example.backend.Entity.Agendamiento;
import com.example.backend.Entity.Reserva;
import com.example.backend.Entity.Vehiculo;
import com.example.backend.Entity.Cliente;
import com.example.backend.Entity.Sucursal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgendamientoRowMapper implements RowMapper<Agendamiento> {

    @Override
    public Agendamiento mapRow(ResultSet rs, int rowNum) throws SQLException {
        Agendamiento agendamiento = new Agendamiento();

        // Mapear columnas del Agendamiento
        agendamiento.setId(rs.getLong("id"));
        agendamiento.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        agendamiento.setFechaFinalizacion(rs.getDate("fecha_finalizacion").toLocalDate());

        if (rs.getDate("proxima_fecha_disponible") != null) {
            agendamiento.setProximaFechaDisponible(rs.getDate("proxima_fecha_disponible").toLocalDate());
        }

        // Mapear entidad Reserva
        Reserva reserva = new Reserva();
        reserva.setId(rs.getLong("id_reserva"));
        agendamiento.setReserva(reserva);

        // Mapear entidad Vehiculo
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(rs.getLong("id_vehiculo"));
        vehiculo.setMarca(rs.getString("marca_vehiculo"));
        vehiculo.setModelo(rs.getString("modelo_vehiculo"));
        vehiculo.setPatente(rs.getString("patente_vehiculo"));
        agendamiento.setVehiculo(vehiculo);

        // Mapear entidad Cliente
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id_cliente"));
        cliente.setNombre(rs.getString("nombre_cliente"));
        agendamiento.setCliente(cliente);

        // Mapear Sucursal de Retiro
        Sucursal sucursalRetiro = new Sucursal();
        sucursalRetiro.setIdSucursal(rs.getLong("id_sucursal_retiro"));
        sucursalRetiro.setNombreSucursal(rs.getString("nombre_sucursal_retiro"));
        sucursalRetiro.setCiudadSucursal(rs.getString("ciudad_sucursal_retiro"));
        agendamiento.setSucursalRetiro(sucursalRetiro);

        // Mapear Sucursal de Devolución
        Sucursal sucursalDevolucion = new Sucursal();
        sucursalDevolucion.setIdSucursal(rs.getLong("id_sucursal_devolucion"));
        sucursalDevolucion.setNombreSucursal(rs.getString("nombre_sucursal_devolucion"));
        sucursalDevolucion.setCiudadSucursal(rs.getString("ciudad_sucursal_devolucion"));
        agendamiento.setSucursalDevolucion(sucursalDevolucion);

        return agendamiento;
    }
}
