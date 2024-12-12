package com.example.backend.Repository;

import com.example.backend.Entity.Cliente;
import com.example.backend.RowMappers.ClienteRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository //maneja las instancias
public class ClienteRepositoryImplement implements ClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate; //Java Database Connectivity

    @Override
    public Optional<Cliente> findById(Long id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        Cliente cliente = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ClienteRowMapper());
        return Optional.ofNullable(cliente);
    }

    @Override
    public Cliente findByEmail(String email) {
        String sql = "SELECT * FROM cliente WHERE email = ?";
        try {
            Cliente cliente = jdbcTemplate.queryForObject(sql, new Object[]{email}, new ClienteRowMapper());
            return cliente;
        } catch (EmptyResultDataAccessException e) {// la excepción EmptyResultDataAccessException se da cuando la consulta no encuentra el dato

            return null;
        }
    }

    @Override
    public Cliente findByRut(String rut) {
        String sql = "SELECT * FROM cliente WHERE rut = ?";
        try {
            Cliente cliente = jdbcTemplate.queryForObject(sql, new Object[]{rut}, new ClienteRowMapper());
            return cliente;
        } catch (EmptyResultDataAccessException e) { // la excepción EmptyResultDataAccessException se da cuando la consulta no encuentra el dato

            return null;
        }
    }

    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM cliente";
      try {
          List<Cliente> clientes = jdbcTemplate.query(sql, new ClienteRowMapper());
          return clientes;
      }catch (EmptyResultDataAccessException e) {
          return new ArrayList<>();
      }
    }

    @Override
    public void save(Cliente cliente) {
        String sql = "INSERT INTO cliente (direccion, email, fecha_nacimiento, nombre, password, rut, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getDireccion(), cliente.getEmail(),  cliente.getFechaNacimiento(), cliente.getNombre(), cliente.getPassword(),
                cliente.getRut(), cliente.getTelefono());
    }

}
