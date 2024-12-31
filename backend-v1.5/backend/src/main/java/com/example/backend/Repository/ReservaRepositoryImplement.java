package com.example.backend.Repository;

import com.example.backend.Entity.Reserva;

import com.example.backend.RowMappers.ReservaRowMapper;
import com.example.backend.RowMappers.VehiculoReferenciaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ReservaRepositoryImplement implements ReservaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Reserva findById(int id) {
        String sql = "SELECT * FROM reservas WHERE id_reserva = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ReservaRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Reserva findReservaByDate(LocalDate date) {
        String sql = "SELECT * FROM reservas WHERE fecha_reserva = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ReservaRowMapper(), date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /*
    @Override
    public Long save(Reserva reserva) {
        // Consulta para verificar si ya existe una reserva con el mismo cliente y las mismas fechas
        String checkSql = "SELECT id_reserva FROM reservas WHERE id_cliente = ? AND fecha_inicio_reserva = ? AND fecha_termino_reserva = ?";

        // Ejecutar la consulta y obtener la lista de reservas
        List<Reserva> reservasExistentes = jdbcTemplate.query(checkSql, new Object[]{
                reserva.getCliente().getIdCliente(),
                java.sql.Date.valueOf(reserva.getFechaInicioReserva()),
                java.sql.Date.valueOf(reserva.getFechaTerminoReserva())
        },  new ReservaRowMapper());

        // Si la lista no está vacía, lanzar una excepción con el ID de la reserva duplicada
        if (!reservasExistentes.isEmpty()) {
            // Obtener el ID de la primera reserva duplicada
            Long idReservaDuplicada = reservasExistentes.get(0).getId_reserva();
            throw new IllegalArgumentException("Ya existe una reserva con las mismas fechas para este cliente. ID de reserva duplicada: " + idReservaDuplicada);
        }

        // Si no existe una reserva con las mismas fechas, proceder con la inserción
        String sql = "INSERT INTO reservas " +
                "(id_cliente, id_sucursal_retiro, id_sucursal_devolucion, id_vehiculo_referencia, " +
                "costo_total, fecha_inicio_reserva, fecha_termino_reserva, fecha_reserva, reserva_finalizada, pago_reserva) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // KeyHolder para capturar el ID generado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Ejecutar la consulta y capturar la clave generada
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_reserva"});
            ps.setLong(1, reserva.getCliente().getIdCliente());
            ps.setLong(2, reserva.getSucursalRetiro().getIdSucursal());
            ps.setLong(3, reserva.getSucursalDevolucion().getIdSucursal());
            ps.setLong(4, reserva.getVehiculoAsignado().getIdVehiculoReferencia());
            ps.setDouble(5, reserva.getCostoTotal());
            ps.setDate(6, java.sql.Date.valueOf(reserva.getFechaInicioReserva()));
            ps.setDate(7, java.sql.Date.valueOf(reserva.getFechaTerminoReserva()));
            ps.setDate(8, java.sql.Date.valueOf(reserva.getFechaReserva()));
            ps.setBoolean(9, reserva.getReservaFinalizada());
            ps.setBoolean(10, reserva.getPagoReserva());
            return ps;
        }, keyHolder);

        // Retornar el ID generado
        return keyHolder.getKey().longValue();
    }
    */

    @Override
    public Long save(Reserva reserva) {
        // Verificar si ya existe una reserva con el mismo cliente y fechas solapadas
        String checkSql = "SELECT id_reserva FROM reservas WHERE id_cliente = ? " +
                "AND ((fecha_inicio_reserva BETWEEN ? AND ?) OR (fecha_termino_reserva BETWEEN ? AND ?) " +
                "OR (? BETWEEN fecha_inicio_reserva AND fecha_termino_reserva) " +
                "OR (? BETWEEN fecha_inicio_reserva AND fecha_termino_reserva))";

        List<Long> existingReservations = jdbcTemplate.queryForList(checkSql, Long.class,
                reserva.getCliente().getIdCliente(),
                java.sql.Date.valueOf(reserva.getFechaInicioReserva()),
                java.sql.Date.valueOf(reserva.getFechaTerminoReserva()),
                java.sql.Date.valueOf(reserva.getFechaInicioReserva()),
                java.sql.Date.valueOf(reserva.getFechaTerminoReserva()),
                java.sql.Date.valueOf(reserva.getFechaInicioReserva()),
                java.sql.Date.valueOf(reserva.getFechaTerminoReserva()));

        // Si existen reservas que se solapan, lanzar una excepción
        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("Ya existe una reserva con las fechas solapadas para este cliente. Reserva repetida con ID: " + existingReservations.get(0));
        }

        // Si no hay reservas solapadas, proceder con la inserción
        String sql = "INSERT INTO reservas " +
                "(id_cliente, id_sucursal_retiro, id_sucursal_devolucion, id_vehiculo_referencia, " +
                "costo_total, fecha_inicio_reserva, fecha_termino_reserva, fecha_reserva, reserva_finalizada, pago_reserva) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // KeyHolder para capturar el ID generado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Ejecutar la consulta y capturar la clave generada
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_reserva"});
            ps.setLong(1, reserva.getCliente().getIdCliente());
            ps.setLong(2, reserva.getSucursalRetiro().getIdSucursal());
            ps.setLong(3, reserva.getSucursalDevolucion().getIdSucursal());
            ps.setLong(4, reserva.getVehiculoAsignado().getIdVehiculoReferencia());
            ps.setDouble(5, reserva.getCostoTotal());
            ps.setDate(6, java.sql.Date.valueOf(reserva.getFechaInicioReserva()));
            ps.setDate(7, java.sql.Date.valueOf(reserva.getFechaTerminoReserva()));
            ps.setDate(8, java.sql.Date.valueOf(reserva.getFechaReserva()));
            ps.setBoolean(9, reserva.getReservaFinalizada());
            ps.setBoolean(10, reserva.getPagoReserva());
            return ps;
        }, keyHolder);

        // Retornar el ID generado
        return keyHolder.getKey().longValue();
    }


    @Override
    public List<Reserva> findByBetwenDates(LocalDate from, LocalDate to) {
        String sql = " SELECT * FROM reservas WHERE fecha_reserva BETWEEN ? AND ? ";
        try {
            return jdbcTemplate.query(sql, new ReservaRowMapper(), from, to);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Long findReservaByIdCliente(Long clienteId) {
        String sql = "SELECT r.id_reserva FROM reservas r JOIN cliente c ON r.id_cliente = c.id_cliente WHERE c.id_cliente = ?";

        try {
            // Retornamos directamente el ID de la reserva como Long
            return jdbcTemplate.queryForObject(sql, new Object[]{clienteId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            // Si no se encuentra, retornamos null
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        jdbcTemplate.update (sql, id);
    }

}
