package com.example.backend.RowMappers;

import com.example.backend.Entity.Cliente;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteRowMapper implements RowMapper<Cliente> {

        @Override
        public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setIdCliente (rs.getLong("id_cliente")); //el string debe ser el mismo nombre del atributo en la base de datos.
            cliente.setNombre(rs.getString("nombre"));
            cliente.setEmail(rs.getString("email"));
            cliente.setRut(rs.getString("rut"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setDireccion(rs.getString("direccion"));
            cliente.setPassword(rs.getString("password"));
            cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            return cliente;
        }
}
