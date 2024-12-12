package com.example.backend.RowMappers;
import com.example.backend.Entity.Sucursal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SucursalRowMapper implements RowMapper<Sucursal> {

    @Override
    public Sucursal mapRow(ResultSet rs, int rowNum) throws SQLException {
         Sucursal sucursal = new Sucursal();
         sucursal.setIdSucursal(rs.getInt("id_sucursal"));
         sucursal.setNombreSucursal(rs.getString("nombre_sucursal"));
         sucursal.setCiudadSucursal(rs.getString("ciudad_sucursal"));
         sucursal.setTelefonoSucursal(rs.getString("telefono_sucursal"));
         return sucursal;
    }
}
