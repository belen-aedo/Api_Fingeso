package com.example.backend.Repository;


import com.example.backend.Entity.Vehiculo;
import com.example.backend.RowMappers.VehiculoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    /**
     * obtener los vehículos cuya fecha termino de reserva más una semana sea menor a la fecha de retiro y que la sucursal de devolución sea igual
     * a la sucursal de retiro y los vehiculo que no estén reservados
     * @param fechaRetiro
     * @param idSucursalRetiro
     * @return
     */
    @Override
    public List<Vehiculo> findBySucursalFecha(LocalDate fechaRetiro, long idSucursalRetiro) {
        String sql =
         " SELECT v.id, v.patente, v.estado_vehiculo " +
         " FROM vehiculo v " +
         " JOIN reservas r ON r.id_reserva = v.id_reserva " +
         " JOIN sucursal s ON r.id_sucursal_devolucion = s.id_sucursal " +
         " WHERE fecha_termino_reserva + (INTERVAL  '1 week') < ? " +
         " AND s.id_sucursal = ? ; " ;
        try {
            // Ejecutar la consulta con los parámetros
            return jdbcTemplate.query(sql, new VehiculoRowMapperSimplify(), fechaRetiro , idSucursalRetiro);
        } catch (EmptyResultDataAccessException e) {
            // Si no hay resultados, devolver una lista vacía
            return new ArrayList<>();
        }
    }
}

//        String sql ="SELECT v.id, v.patente, v.estado_vehiculo " +
//                    "FROM vehiculo v " +
//                    "JOIN reservas r ON r.id_reserva = v.id_reserva " +
//                    "JOIN sucursal s ON r.id_sucursal_devolucion = s.id_sucursal " +
//                    "WHERE fecha_termino_reserva + INTERVAL '1 week' < ? " +
//                    "AND s.id_sucursal = ? ; " ;