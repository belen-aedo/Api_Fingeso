package com.example.backend.Repository;

import com.example.backend.Entity.Agendamiento;
import com.example.backend.Entity.Sucursal;
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
    public void save(LocalDate fechaInicio, LocalDate fechaFin, Sucursal sRetiro, Sucursal sDevolucion) {
        String sql = "INSERT INTO agendamiento (fecha_inicio, fecha_finalizacion, id_sucursal_retiro, id_sucursal_devolucion) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, fechaInicio, fechaFin, sRetiro.getIdSucursal(), sDevolucion.getIdSucursal());
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
