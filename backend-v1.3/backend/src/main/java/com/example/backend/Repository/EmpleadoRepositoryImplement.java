package com.example.backend.Repository;

import com.example.backend.Entity.Empleado;
import com.example.backend.Entity.Sucursal;
import com.example.backend.RowMappers.SucursalRowMapper;
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
        /*
        String sql = "select * from sucursal where id_sucursal = ?";
        try {
            Sucursal sucursal = jdbcTemplate.queryForObject(sql, new Object[]{id}, new SucursalRowMapper());
            return sucursal;
        }catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontro el sucursal con el id " + id+ "\n" + e);
            return null;
        }

         */
        return null;
    }

    @Override
    public Empleado findByEmail(String email) {
        return null;
    }

    @Override
    public Empleado findByPassword(String password) {
        return null;
    }

    @Override
    public Empleado findByrol(String rol) {
        return null;
    }

    @Override
    public void save(Empleado empleado) {

    }
}
