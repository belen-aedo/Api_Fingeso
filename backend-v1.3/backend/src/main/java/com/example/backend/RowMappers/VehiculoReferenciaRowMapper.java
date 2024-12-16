package com.example.backend.RowMappers;


import com.example.backend.Entity.VehiculoReferencia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VehiculoReferenciaRowMapper implements RowMapper<VehiculoReferencia> {
    // Constantes para los nombres de columnas
    private static final String ID_VEHICULO_REFERENCIA = "id_vehiculo_referencia";
    private static final String MODELO = "modelo";
    private static final String DESCRIPCION_PUBLICACION = "descripcion_publicacion";
    private static final String ESTADO = "estado";
    private static final String COSTO_RESERVA = "costo_reserva_vehiculo";
    private static final String COSTO_ARRIENDO = "costo_arriendo_vehiculo";
    private static final String CAPACIDAD_PASAJEROS = "capacidad_pasajeros";
    private static final String CANTIDAD_PUERTAS = "cantidad_puertas";
    private static final String TIPO_COMBUSTIBLE = "tipo_combustible";
    private static final String MECANICO_AUTOMATICO = "mecanico_automatico";
    private static final String ACRISS = "acriss";
    private static final String URL = "url";

    @Override
    public VehiculoReferencia mapRow(ResultSet rs, int rowNum) throws SQLException {
        VehiculoReferencia vehiculoRef = new VehiculoReferencia();
        // Mapear columnas con validación de nulos
        vehiculoRef.setIdVehiculoReferencia(rs.getObject(ID_VEHICULO_REFERENCIA, Long.class)); // Maneja posibles valores nulos
        vehiculoRef.setModelo(rs.getString(MODELO));
        vehiculoRef.setDescripcionPublicacion(rs.getString(DESCRIPCION_PUBLICACION));
        vehiculoRef.setEstado(rs.getBoolean(ESTADO));
        vehiculoRef.setCostoReservaVehiculo(getNullableDouble(rs, COSTO_RESERVA));
        vehiculoRef.setCostoArriendolVehiculo(getNullableDouble(rs, COSTO_ARRIENDO));
        vehiculoRef.setCapacidadPasajeros(getNullableInt(rs, CAPACIDAD_PASAJEROS));
        vehiculoRef.setCantidadPuertas(getNullableInt(rs, CANTIDAD_PUERTAS));
        vehiculoRef.setTipoCombustible(rs.getString(TIPO_COMBUSTIBLE));
        vehiculoRef.setMecanico_automatico(rs.getString(MECANICO_AUTOMATICO));
        vehiculoRef.setAcriss(rs.getString(ACRISS));
        vehiculoRef.setUrl(rs.getString(URL));
        return vehiculoRef;
    }

    // Métodos auxiliares para manejar valores nulos
    private Double getNullableDouble(ResultSet rs, String columnName) throws SQLException {
        double value = rs.getDouble(columnName);
        return rs.wasNull() ? null : value;
    }

    private Integer getNullableInt(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return rs.wasNull() ? null : value;
    }


}
