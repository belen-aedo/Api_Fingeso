package com.example.backend.RowMappers;

import com.example.backend.Entity.Arriendo;
import com.example.backend.Entity.Reserva;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class ArriendoRowMapper implements RowMapper<Arriendo> {

    @Override
    public Arriendo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Arriendo arriendo = new Arriendo();

        //Mapeo

        arriendo.setIdArriendo(rs.getInt("id_arriendo"));
        arriendo.setRutCliente(rs.getString("rut_cliente"));
        arriendo.setCostoTotal(rs.getDouble("costo_total"));

        //Fechas
        arriendo.setFechaArriendo(rs.getObject("fecha_arriendo", LocalDate.class));
        arriendo.setFechaInicioArriendo(rs.getObject("fecha_inicio_arriendo", LocalDate.class));
        arriendo.setFechaTerminoArriendo(rs.getObject("fecha_termino_arriendo", LocalDate.class));

        // Estado
        arriendo.setEstadoArriendo(rs.getBoolean("estado_arriendo"));

        Reserva reserva = new Reserva();
        reserva.setId_reserva(rs.getLong("id_reserva"));
        arriendo.setReserva(reserva);

        Sucursal sucursalD = new Sucursal();
        sucursalD.setIdSucursal(rs.getLong("id_sucursal_devolucion"));
        arriendo.setSucursalDevolucion(sucursalD);


        Sucursal sucursalR = new Sucursal();
        sucursalR.setIdSucursal(rs.getLong("id_sucursal_retiro"));
        arriendo.setSucursalRetiro(sucursalR);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(rs.getLong("id_vehiculo"));
        arriendo.setVehiculo(vehiculo);

        return arriendo;
    }
}
