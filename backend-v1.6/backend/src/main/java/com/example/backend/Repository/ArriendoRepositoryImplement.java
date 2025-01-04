package com.example.backend.Repository;

import com.example.backend.Entity.Arriendo;
import com.example.backend.RowMappers.AgendamientoRowMapper;
import com.example.backend.RowMappers.ArriendoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArriendoRepositoryImplement implements ArriendoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Arriendo findArriendoByIdReserva(Long id_reserva) {

        String sql = " SELECT a.* FROM arriendo a WHERE a.id_reserva = ? ";

        try {
            Arriendo arriendo = jdbcTemplate.queryForObject(sql, new Object[]{id_reserva}, new ArriendoRowMapper());
            return arriendo;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void ChangePendingStatusById(Long id_arriendo, Boolean estado) {
        String sql = " UPDATE arriendo SET pendiente_arriendo = ? WHERE id_arriendo = ?";
        jdbcTemplate.update(sql, estado, id_arriendo);
    }

    @Override
    public void ChangeState(Long id_arriendo, Boolean estado) {
        String sql = " UPDATE arriendo SET estado_arriendo = ? WHERE id_arriendo = ?";
        jdbcTemplate.update(sql, estado, id_arriendo);
    }

    @Override
    public void updateCostWithPenalty(Long idReserva, Double multa) {
        String sql = "UPDATE arriendo SET costo_total = costo_total + ? WHERE id_reserva = ?";
        jdbcTemplate.update(sql, multa, idReserva);
    }

}
