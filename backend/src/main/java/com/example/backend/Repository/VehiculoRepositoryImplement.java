package com.example.backend.Repository;


import com.example.backend.Entity.Vehiculo;
import com.example.backend.RowMappers.VehiculoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VehiculoRepositoryImplement implements VehiculoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * El vehiculo que se deberia obtener contendria las sucursales pero solo son sus id, con los datos que las componen, al igual que las reservas
     * @param patente patente del vehiculo
     * @return vehiculo que puede o no tener una reserva asociada
     */
    @Override
    public Vehiculo findByPatente(String patente) {
        String sql =
                "SELECT  v.* , s.*" +
                "FROM vehiculo v INNER JOIN sucursal s on v.id_sucursal = s.id_sucursal " +
                "WHERE patente = ?; ";
        try {
            Vehiculo vehiculo = jdbcTemplate.queryForObject(sql, new Object[]{patente}, new VehiculoRowMapper());

            return vehiculo;

        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public List<Vehiculo> findBySucursal(String nombreSucursal) {
        String sql = "SELECT v.*, s.* " +
                "FROM vehiculo v INNER JOIN sucursal s ON v.id_sucursal = s.id_sucursal " +
                "WHERE s.nombre_sucursal = ?;";
        try {
            return jdbcTemplate.query(sql, new VehiculoRowMapper(), nombreSucursal);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Vehiculo> ObtenerPorDisponibilidad(String placa) {
        return List.of();
    }
}
