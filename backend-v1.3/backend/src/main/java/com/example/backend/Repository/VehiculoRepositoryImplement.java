package com.example.backend.Repository;


import com.example.backend.Entity.Vehiculo;
import com.example.backend.Entity.VehiculoReferencia;
import com.example.backend.RowMappers.VehiculoReferenciaRowMapper;
import com.example.backend.RowMappers.VehiculoRowMapper;
import com.example.backend.RowMappers.VehiculoRowMapperSimplify;
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
     *
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
     *
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
                        " AND s.id_sucursal = ? ; ";
        try {
            // Ejecutar la consulta con los parámetros
            return jdbcTemplate.query(sql, new VehiculoRowMapperSimplify(), fechaRetiro, idSucursalRetiro);
        } catch (EmptyResultDataAccessException e) {
            // Si no hay resultados, devolver una lista vacía
            return new ArrayList<>();
        }
    }

/**
 * Busca los vehículos disponibles en una sucursal específica para un rango de fechas dado.
 *
 * Este metodo consulta la base de datos para obtener los vehículos disponibles en la sucursal
 * especificada que no estén reservados en las fechas indicadas. Si un vehículo tiene agendamientos
 * en las fechas proporcionadas (ya sea solapándose con el inicio o el final del rango), no será
 * considerado disponible. Además, si un vehículo no tiene registros en la tabla de agendamientos,
 * también será incluido en los resultados.
 *
 * @param fechaRetiro La fecha de retiro del vehículo (inicio del rango de fechas).
 * @param fechaDevolucion La fecha de devolución del vehículo (fin del rango de fechas).
 * @param nombreSucursal El nombre de la sucursal donde se busca la disponibilidad de los vehículos.
 * @return Una lista de vehículos disponibles que cumplen con los criterios de fecha y sucursal.
 *         Si no hay vehículos disponibles, se devuelve una lista vacía.
 * @throws EmptyResultDataAccessException Si ocurre un error al ejecutar la consulta en la base de datos.
 */
    @Override
    public List<Vehiculo> findByDates(LocalDate fechaRetiro, LocalDate fechaDevolucion, String nombreSucursal) {
        String sql =
                " SELECT v.*, s.* " + "FROM vehiculo v " +
                        " JOIN sucursal s ON v.id_sucursal = s.id_sucursal " +
                        " LEFT JOIN agendamiento a ON a.id_vehiculo = v.id " +
                        "   AND ( " +
                        "       (a.fecha_inicio <= ? AND a.proxima_fecha_disponible >= ?) OR " +
                        "       (a.fecha_inicio <= ? AND a.proxima_fecha_disponible >= ?) OR " +
                        "       (a.fecha_inicio >= ? AND a.fecha_inicio <= ?) " +
                        "   ) " +
                        " WHERE s.nombre_sucursal = ? " +
                        " AND a.id IS NULL; ";
        try {
            return jdbcTemplate.query(
                    sql,
                    new VehiculoRowMapper(),
                    fechaRetiro, fechaRetiro,
                    fechaDevolucion, fechaDevolucion,
                    fechaRetiro, fechaDevolucion,
                    nombreSucursal
            );
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<VehiculoReferencia> getReferenciasPorModelos(List<String> modelos) {
        String sql = "SELECT v.*" +
                " FROM vehiculo_referencia v" +
                " WHERE v.modelo IN (:modelos)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("modelos", modelos);
        try {
            return jdbcTemplate.query(sql, (PreparedStatementSetter) parameters, new VehiculoReferenciaRowMapper());

        } catch (EmptyResultDataAccessException e) {
            return List.of(); // Devuelve una lista vacía si no hay resultados
        }
    }

    public VehiculoReferencia getReferencias(String modelo) {
        String sql = "SELECT v.*" +
                " FROM vehiculo_referencia v" +
                " WHERE v.modelo = ?";
        try {
            // Ejecutar la consulta con los parámetros
            return jdbcTemplate.queryForObject(sql, new Object[]{modelo} ,new VehiculoReferenciaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // Si no hay resultados, devolver una lista vacía
            return null;
        }
    }



}
