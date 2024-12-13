package com.example.backend.Repository;

import com.example.backend.Entity.Empleado;
import com.example.backend.RowMappers.EmpleadoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoRepositoryImplement implements EmpleadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Empleado findById(long id) {
        String sql =
            " SELECT e.*, s.nombre_sucursal, s.ciudad_sucursal, s.telefono_sucursal " +
                    "FROM empleado e INNER JOIN sucursal s ON e.id_sucursal = s.id_sucursal " +
                    "WHERE e.id_empleado = ?; ";
        try {
            Empleado empleado = jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmpleadoRowMapper());
            return empleado;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Empleado findByEmail(String email) {
        String sql =
                " SELECT e.*, s.nombre_sucursal, s.ciudad_sucursal, s.telefono_sucursal " +
                        "FROM empleado e INNER JOIN sucursal s ON e.id_sucursal = s.id_sucursal " +
                        "WHERE e.email = ?; ";
        try {
            Empleado empleado = jdbcTemplate.queryForObject(sql, new Object[]{email}, new EmpleadoRowMapper());
            return empleado;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Empleado findByRut(String rut) {
        String sql =
                " SELECT e.*, s.nombre_sucursal, s.ciudad_sucursal, s.telefono_sucursal " +
                        "FROM empleado e INNER JOIN sucursal s ON e.id_sucursal = s.id_sucursal " +
                        "WHERE e.rut = ?; ";
        try {
            Empleado empleado = jdbcTemplate.queryForObject(sql, new Object[]{rut}, new EmpleadoRowMapper());
            return empleado;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(Empleado empleado) {
        String sql = "INSERT INTO empleado (nombre, rut, telefono, rol, email, password, id_sucursal) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                empleado.getNombre(),
                empleado.getRut(),
                empleado.getTelefono(),
                empleado.getRol(),
                empleado.getEmail(),
                empleado.getPassword(),
                empleado.getSucursal().getIdSucursal()
        );
    }
}
