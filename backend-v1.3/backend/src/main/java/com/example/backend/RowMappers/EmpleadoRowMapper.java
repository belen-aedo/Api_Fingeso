package com.example.backend.RowMappers;

import com.example.backend.Entity.Cliente;
import com.example.backend.Entity.Empleado;
import com.example.backend.Entity.Sucursal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoRowMapper implements RowMapper<Empleado> {

    @Override
    public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setId(rs.getInt("id"));
        empleado.setNombre(rs.getString("nombre"));
        empleado.setRol(rs.getString("rol"));
        empleado.setRut(rs.getString("rut"));
        empleado.setCorreoElectronico(rs.getString("correo_electronico"));
        empleado.setPassword(rs.getString("password"));
        empleado.setTelefono(rs.getString("telefono"));
        empleado.setSucursal(rs.getObject("sucursal", Sucursal.class));
        return empleado;
    }
}
