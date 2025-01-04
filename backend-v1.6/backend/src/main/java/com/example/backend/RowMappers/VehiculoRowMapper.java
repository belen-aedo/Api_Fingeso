package com.example.backend.RowMappers;

import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoRowMapper implements RowMapper<Vehiculo> {

    @Override
    public Vehiculo mapRow(ResultSet rs, int rowNum) throws SQLException {

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setId(rs.getLong("id"));
        vehiculo.setMarca(rs.getString("marca"));
        vehiculo.setModelo(rs.getString("modelo"));
        vehiculo.setEstadoVehiculo(rs.getString("estado_vehiculo").charAt(0));
        vehiculo.setColorPrincipal(rs.getString("color_principal"));
        vehiculo.setKilometrajeVehiculo(rs.getDouble("kilometraje_vehiculo"));
        vehiculo.setPatente(rs.getString("patente"));
        vehiculo.setYear(rs.getInt("year"));

        // obtengo la sucursal que esta el vehiculo
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(rs.getLong("id_sucursal"));
        sucursal.setNombreSucursal(rs.getString("nombre_sucursal"));
        sucursal.setCiudadSucursal(rs.getString("ciudad_sucursal"));
        sucursal.setTelefonoSucursal(rs.getString("telefono_sucursal"));

        vehiculo.setUbicacionActual(sucursal);
        return vehiculo;
    }
}