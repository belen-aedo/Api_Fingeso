package com.example.backend.Repository;

import com.example.backend.Entity.*;
import com.example.backend.RowMappers.AgendamientoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class AgendamientoRepositoryImplement implements AgendamientoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaDipo, Sucursal sRetiro, Sucursal sDevolucion, Cliente cliente, Vehiculo vehiculo, Reserva reserva) {
        String sql = "INSERT INTO agendamiento (fecha_inicio, fecha_finalizacion, proxima_fecha_disponible, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, fechaInicio, fechaFin, fechaDipo, cliente.getIdCliente(), reserva.getId_reserva(), sDevolucion.getIdSucursal(), sRetiro.getIdSucursal(), vehiculo.getId());
    }

    @Override
    public Agendamiento findAgendamientoByIdUsuario(long idUsuario) {
        String sql = "SELECT * FROM agendamiento WHERE id_cliente = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new AgendamientoRowMapper(), idUsuario);

        } catch (EmptyResultDataAccessException e){
            return null;

        }
    }

    @Override
    public Agendamiento findAgendamientoByIdReserva(long idReserva) {
        String sql = "SELECT * FROM agendamiento WHERE id_reserva = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new AgendamientoRowMapper(), idReserva);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

}
