package com.example.backend.RowMappers;

import com.example.backend.Entity.Empleado;
import com.example.backend.Entity.Sucursal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoRowMapper implements RowMapper<Empleado> {

    JdbcTemplate jdbcTemplate;

    /**
     * como empleado necesita los datos de sucursal entonces se requieren de los datos de la sucursal
     * @param rs los datos de la consulta sql utilizando SELECT
     * @param rowNum numeros de columnas obtenidos
     * @return un empleado
     * @throws SQLException
     */
    @Override
    public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
            Empleado empleado = new Empleado();
            empleado.setIdEmpleado(rs.getLong("id_empleado"));
            empleado.setNombre(rs.getString("nombre"));
            empleado.setRut(rs.getString("rut"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setRol(rs.getString("rol"));
            empleado.setEmail(rs.getString("email"));
            empleado.setPassword(rs.getString("password"));

            Sucursal sucursal = new Sucursal();
            sucursal.setIdSucursal(rs.getLong("id_sucursal"));
            sucursal.setNombreSucursal(rs.getString("nombre_sucursal"));
            sucursal.setCiudadSucursal(rs.getString("ciudad_sucursal"));
            sucursal.setTelefonoSucursal(rs.getString("telefono_sucursal"));

            empleado.setSucursal(sucursal);
            return empleado;
    }
}

