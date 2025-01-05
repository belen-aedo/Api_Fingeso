package com.example.backend.Repository;

import com.example.backend.Entity.Cliente;
import com.example.backend.Entity.Reserva;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository {

    Optional<Cliente> findById(Long id); // es Optional para que no retorne un null
    List<Cliente> findAll();
    Cliente findByEmail(String email);
    Cliente findByRut(String rut);
    void save(Cliente cliente);
    List<Reserva> GetReservasByIdCliente(Long idCliente);
}


