package com.example.backend.Repository;


import com.example.backend.Entity.Vehiculo;
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

    // filtrado de vehiculo disponibles por fecha, filtra los autos cuya fecha retiro sea mayor a la fecha de disponible o la fecha de
    // devolución sea menor a la fecha de inicio de los arriendos

    /**
     * @param fechaRetiro     fecha inicio
     * @param fechaDevolucion fecha fin
     * @return
     */
    @Override
    public List<Vehiculo> findByDates(LocalDate fechaRetiro, LocalDate fechaDevolucion, String nombreSucursal) {
        String sql =
                "SELECT v.* " +
                        "FROM vehiculo v " +
                        "JOIN sucursal s ON v.id_sucursal = s.id_sucursal " +
                        "WHERE s.nombre_sucursal = ? " +
                        "AND NOT EXISTS ( " +
                        "    SELECT 1 " +
                        "    FROM agendamiento a " +
                        "    WHERE a.id_vehiculo = v.id_vehiculo " +
                        "    AND ( " +
                        "        (a.fecha_inicio <= ? AND a.proxima_fecha_disponible >= ?) OR " +  // Verifica solapamiento con el inicio
                        "        (a.fecha_inicio <= ? AND a.proxima_fecha_disponible >= ?) OR " +  // Verifica solapamiento con el final
                        "        (a.fecha_inicio >= ? AND a.fecha_inicio <= ?) " +  // Verifica que la nueva fecha de retiro no se solape con un agendamiento previo
                        "    ) " +
                        ")";
        try {
            // Ejecutar la consulta con los parámetros
            return jdbcTemplate.query(sql, new VehiculoRowMapper(),
                    nombreSucursal,
                    fechaRetiro, fechaRetiro,
                    fechaDevolucion, fechaDevolucion,
                    fechaRetiro, fechaDevolucion);
        } catch (EmptyResultDataAccessException e) {
            // Si no hay resultados, devolver una lista vacía
            return new ArrayList<>();
        }
    }
}
/*
public List<Vehiculo> findAvailableVehicles(LocalDate fechaRetiro, LocalDate fechaDevolucion, String nombreSucursal) {
    String sql =
            "SELECT v.* " +
            "FROM vehiculo v " +
            "JOIN sucursal s ON v.id_sucursal = s.id_sucursal " +
            "WHERE s.nombre_sucursal = ? " +
            "  AND NOT EXISTS ( " +
            "    SELECT 1 " +
            "    FROM agendamiento a " +
            "    WHERE a.id_vehiculo = v.id_vehiculo " +
            "      AND ( " +
            "          (a.fecha_inicio <= ? AND a.proxima_fecha_disponible > ?) OR " + // Solapamiento con el retiro
            "          (a.fecha_inicio < ? AND a.proxima_fecha_disponible >= ?) OR " + // Solapamiento con la devolución
            "          (a.fecha_inicio >= ? AND a.proxima_fecha_disponible <= ?) " + // Solapamiento con otros agendamientos
            "      ) " +
            "  ) " +
            "  AND NOT EXISTS ( " +
            "    SELECT 1 " +
            "    FROM reserva r " +
            "    WHERE r.id_vehiculo = v.id_vehiculo " +
            "      AND r.reserva_finalizada = false " + // La reserva debe no estar finalizada
            "      AND ( " +
            "          (r.fecha_inicio_reserva <= ? AND r.fecha_termino_reserva > ?) OR " + // Solapamiento con el retiro
            "          (r.fecha_inicio_reserva < ? AND r.fecha_termino_reserva >= ?) " + // Solapamiento con la devolución
            "      ) " +
            "  )";

    try {
        // Ejecutar la consulta para obtener los vehículos disponibles
        return jdbcTemplate.query(sql, new VehiculoRowMapper(),
                nombreSucursal,
                fechaRetiro, fechaDevolucion,
                fechaDevolucion, fechaDevolucion,
                fechaRetiro, fechaDevolucion,
                fechaRetiro, fechaDevolucion);
    } catch (EmptyResultDataAccessException e) {
        return new ArrayList<>();
    }
}


Explicación de la optimización:
1) Única consulta SQL:
    - La consulta SQL ahora filtra directamente los vehículos que no tienen conflictos con los agendamientos ni con las reservas.
    - Condiciones en la subconsulta agendamiento: Verifica si las fechas de retiro o devolución se solapan con algún agendamiento no finalizado del vehículo.
    - Condiciones en la subconsulta reserva: Verifica si las fechas de retiro o devolución se solapan con alguna reserva no finalizada del vehículo.
2) No es necesario filtrar después:
    - Debido a que la consulta SQL ya incluye todas las condiciones de solapamiento (tanto con reservas como con agendamientos), no necesitamos hacer ninguna verificación extra en Java (como en el filtro stream).
    - Solo los vehículos que cumplen con todas las condiciones de disponibilidad son devueltos.
3) Ventajas:
    - Eficiencia: Realizamos la verificación directamente en la base de datos, lo que reduce la carga de trabajo en la aplicación y evita la doble verificación.
    - Simplicidad: El código es más sencillo y claro, ya que solo se necesita realizar una consulta para obtener los vehículos disponibles.
    - Rendimiento: Al delegar la lógica de filtrado a la base de datos, se optimiza la consulta, especialmente si tienes una gran cantidad de vehículos, agendamientos y reservas.
4) Resumen:
    - Con esta optimización, la consulta SQL ahora maneja completamente la lógica de disponibilidad, y solo se hace una llamada a la base de datos, lo que mejora el rendimiento y la claridad del código.
*/

//        String sql ="SELECT v.id, v.patente, v.estado_vehiculo " +
//                    "FROM vehiculo v " +
//                    "JOIN reservas r ON r.id_reserva = v.id_reserva " +
//                    "JOIN sucursal s ON r.id_sucursal_devolucion = s.id_sucursal " +
//                    "WHERE fecha_termino_reserva + INTERVAL '1 week' < ? " +
//                    "AND s.id_sucursal = ? ; " ;