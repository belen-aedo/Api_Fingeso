package com.example.backend.Repository;

import com.example.backend.Entity.Sucursal;
import com.example.backend.RowMappers.SucursalRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SucursalRepositoryImplement implements SucursalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Sucursal findById(long id) {
        String sql = "select * from sucursal where id_sucursal = ?";
        try {
            Sucursal sucursal = jdbcTemplate.queryForObject(sql, new Object[]{id}, new SucursalRowMapper());
            return sucursal;
        }catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontro el sucursal con el id " + id+ "\n" + e);
            return null;
        }
    }

    @Override
    public Sucursal findByNombreSucursal(String nombre) {
        String sql = "select * from sucursal where nombre_sucursal = ?";
        try {
            Sucursal sucursal = jdbcTemplate.queryForObject(sql, new Object[]{nombre}, new SucursalRowMapper());
            return sucursal;
        }catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontro la sucursal " + nombre+ "\n" + e);
            return null;
        }
    }
}
