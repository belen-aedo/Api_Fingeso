package com.example.backend.Repository;

import com.example.backend.Entity.Reserva;

import com.example.backend.RowMappers.ReservaRowMapper;
import com.example.backend.RowMappers.VehiculoReferenciaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

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

    @Override
    public void save(Reserva reserva) {
        String sql = "INSERT INTO reservas " +
                "(id_cliente, id_sucursal_retiro, id_sucursal_devolucion, id_vehiculo_referencia, " +
                "costo_total, fecha_inicio_reserva, fecha_termino_reserva, fecha_reserva, reserva_finalizada, pago_reserva) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Todavia no se hace el arriendo
        jdbcTemplate.update(sql,
                reserva.getCliente().getIdCliente(),
                reserva.getSucursalRetiro().getIdSucursal(),
                reserva.getSucursalDevolucion().getIdSucursal(),
                reserva.getVehiculoAsignado().getIdVehiculoReferencia(),
                reserva.getCostoTotal(),
                reserva.getFechaInicioReserva(),
                reserva.getFechaTerminoReserva(),
                reserva.getFechaReserva(),
                reserva.getReservaFinalizada(),
                reserva.getPagoReserva()
        );
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
