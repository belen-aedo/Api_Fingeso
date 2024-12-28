package com.example.backend.RowMappers;


import com.example.backend.Entity.VehiculoReferencia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VehiculoReferenciaRowMapper implements RowMapper<VehiculoReferencia> {

    @Override
    public VehiculoReferencia mapRow(ResultSet rs, int rowNum) throws SQLException {

        VehiculoReferencia vehiculoRef = new VehiculoReferencia();

        vehiculoRef.setCantidadPuertas(rs.getInt("cantidad_puertas"));
        vehiculoRef.setCapacidadPasajeros(rs.getInt("capacidad_pasajeros"));
        vehiculoRef.setCostoArriendoVehiculo(rs.getDouble("costo_arriendo_vehiculo"));
        vehiculoRef.setCostoReservaVehiculo(rs.getDouble("costo_reserva_vehiculo"));

        vehiculoRef.setIdVehiculoReferencia(rs.getLong("id_vehiculo_referencia"));
        vehiculoRef.setAcriss(rs.getString("acriss"));
        vehiculoRef.setDescripcionPublicacion(rs.getString("descripcion_publicacion"));

        vehiculoRef.setMecanico_automatico(rs.getString("mecanico_automatico"));
        vehiculoRef.setModelo(rs.getString("modelo"));
        vehiculoRef.setTipoCombustible(rs.getString("tipo_combustible"));
        vehiculoRef.setUrl(rs.getString("url"));

        return vehiculoRef;
    }
}
