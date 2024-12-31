package com.example.backend.Repository;


import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Vehiculo;
import com.example.backend.Entity.VehiculoReferencia;
import com.example.backend.RowMappers.VehiculoReferenciaRowMapper;
import com.example.backend.RowMappers.VehiculoRowMapper;
import com.example.backend.RowMappers.VehiculoRowMapperSimplify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

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


/*
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

    @Override
    public List<Vehiculo> findByDates(LocalDate fechaRetiro, LocalDate fechaDevolucion, String nombreSucursal) {
        String sql = " SELECT v.*, s.* " +
                     " FROM vehiculo v " +
                     " JOIN sucursal s ON v.id_sucursal = s.id_sucursal " +
                     " LEFT JOIN agendamiento a ON a.id_vehiculo = v.id " +
                     "   AND ( " +
                "       (a.fecha_inicio <= ? AND a.proxima_fecha_disponible > ?) OR " +
                "       (a.fecha_inicio <= ? AND a.proxima_fecha_disponible >= ?) OR " +
                "       (a.fecha_inicio >= ? AND a.fecha_inicio <= ?) " +
                "   ) " +
                      " WHERE s.nombre_sucursal = ? " +
                      " AND a.id_reserva IS NULL " +
                      " AND v.estado_vehiculo = 'D' ;";
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
*/
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
        String sql = " SELECT v.* " +
                     " FROM vehiculo_referencia v " +
                     " WHERE v.modelo = ? ";
        try {
            // Ejecutar la consulta con los parámetros
            return jdbcTemplate.queryForObject(sql, new Object[]{modelo} ,new VehiculoReferenciaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // Si no hay resultados, devolver una lista vacía
            return null;
        }
    }

    @Override
    public Vehiculo getVehiculoByIdReserva(Long id) {

        String sql = " SELECT v.id, v.patente, v.marca, v.modelo, v.color_principal, v.kilometraje_vehiculo " +
                     " FROM vehiculo v INNER JOIN arriendo a ON v.id = a.id_vehiculo WHERE a.id_reserva = ? ";
        try {

            return jdbcTemplate.queryForObject(sql, new Object[]{id} ,new VehiculoRowMapperSimplify());

        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    @Override
    public void updateKilometrajeById(Long idVehiculo, Double nuevoKilometraje) {

        // Primero, obtener el kilometraje actual del vehículo
        String sqlSelect = "SELECT kilometraje_vehiculo FROM public.vehiculo WHERE id = ?";
        Double kilometrajeActual = jdbcTemplate.queryForObject(sqlSelect, Double.class, idVehiculo);

        // Verificar si el nuevo kilometraje es mayor o igual al actual
        if (nuevoKilometraje < kilometrajeActual) {
            throw new IllegalArgumentException("El nuevo kilometraje debe ser mayor o igual al kilometraje actual.");
        }

        // Si la verificación es exitosa, actualizar el kilometraje
        String sqlUpdate = "UPDATE public.vehiculo SET kilometraje_vehiculo = ? WHERE id = ?";
        jdbcTemplate.update(sqlUpdate, nuevoKilometraje, idVehiculo);
    }



    @Override
    public List<Vehiculo> findByDates(LocalDate fechaRetiro, LocalDate fechaDevolucion, String nombreSucursal) {
        // Consulta SQL para obtener vehículos y agendamientos
        String sql = " SELECT v.*, s.*, a.fecha_inicio, a.proxima_fecha_disponible " +
                " FROM vehiculo v " +
                " JOIN sucursal s ON v.id_sucursal = s.id_sucursal " +
                " LEFT JOIN agendamiento a ON a.id_vehiculo = v.id " +
                " WHERE s.nombre_sucursal = ? " +
                " AND v.estado_vehiculo = 'D'";
        try {
            // Ejecutar consulta y mapear resultados
            List<Vehiculo> vehiculos = jdbcTemplate.query(
                    sql,
                    rs -> {
                        Map<Long, Vehiculo> vehiculoMap = new HashMap<>();
                        Map<Long, List<AbstractMap.SimpleEntry<LocalDate, LocalDate>>> agendamientosMap = new HashMap<>();

                        while (rs.next()) {
                            long vehiculoId = rs.getLong("id");
                            // Mapear o recuperar el vehículo
                            Vehiculo vehiculo = vehiculoMap.computeIfAbsent(vehiculoId, id -> {
                                Vehiculo v = new Vehiculo();
                                v.setId(id);
                                try {
                                    v.setMarca(rs.getString("marca"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    v.setModelo(rs.getString("modelo"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    v.setEstadoVehiculo(rs.getString("estado_vehiculo").charAt(0));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    v.setColorPrincipal(rs.getString("color_principal"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    v.setKilometrajeVehiculo(rs.getDouble("kilometraje_vehiculo"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    v.setPatente(rs.getString("patente"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    v.setYear(rs.getInt("year"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }

                                // Mapear sucursal
                                Sucursal sucursal = new Sucursal();
                                try {
                                    sucursal.setIdSucursal(rs.getLong("id_sucursal"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    sucursal.setNombreSucursal(rs.getString("nombre_sucursal"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    sucursal.setCiudadSucursal(rs.getString("ciudad_sucursal"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    sucursal.setTelefonoSucursal(rs.getString("telefono_sucursal"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                v.setUbicacionActual(sucursal);

                                return v;

                            });

                            // Mapear agendamientos
                            LocalDate fechaInicio = rs.getDate("fecha_inicio") != null
                                    ? rs.getDate("fecha_inicio").toLocalDate()
                                    : null;
                            LocalDate proximaFechaDisponible = rs.getDate("proxima_fecha_disponible") != null
                                    ? rs.getDate("proxima_fecha_disponible").toLocalDate()
                                    : null;

                            if (fechaInicio != null && proximaFechaDisponible != null) {
                                agendamientosMap
                                        .computeIfAbsent(vehiculoId, k -> new ArrayList<>())
                                        .add(new AbstractMap.SimpleEntry<>(fechaInicio, proximaFechaDisponible));
                            }
                        }
                        // Filtrar vehículos conflictivos
                        return vehiculoMap.values().stream()
                                .filter(vehiculo -> {
                                    List<AbstractMap.SimpleEntry<LocalDate, LocalDate>> agendamientos =
                                            agendamientosMap.getOrDefault(vehiculo.getId(), new ArrayList<>());

                                    return agendamientos.stream().noneMatch(agendamiento -> {
                                        LocalDate agendFechaInicio = agendamiento.getKey();
                                        LocalDate agendFechaFin = agendamiento.getValue();

                                        return (fechaRetiro.isBefore(agendFechaFin) && fechaDevolucion.isAfter(agendFechaInicio)) || // Solapamiento
                                                (fechaRetiro.equals(agendFechaInicio) && fechaDevolucion.equals(agendFechaFin));    // Fechas exactas
                                    });
                                })
                                .toList();
                    },
                    nombreSucursal
            );
            return vehiculos;

        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
