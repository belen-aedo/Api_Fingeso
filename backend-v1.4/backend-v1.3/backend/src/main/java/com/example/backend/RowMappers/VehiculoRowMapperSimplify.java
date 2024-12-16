package com.example.backend.RowMappers;

import com.example.backend.Entity.Vehiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoRowMapperSimplify implements RowMapper<Vehiculo> {

    @Override
    public Vehiculo mapRow(ResultSet rs, int rowNum) throws SQLException {

        // Crear una instancia de Vehiculo
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(rs.getLong("id"));
        vehiculo.setPatente(rs.getString("patente"));
        vehiculo.setEstadoVehiculo(rs.getString("estado_vehiculo").charAt(0));
        return vehiculo;
    }
}
