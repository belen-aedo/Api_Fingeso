package com.example.Backend_Api.repositories;

import com.example.Backend_Api.entities.Cliente;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Cliente findByCorreo_registrado(String correo_registrado);

    Cliente findByRut_registrado(String rut_registrado);

    Cliente findById_registrado(long id_registrado);

    @NotNull Cliente save(Cliente cliente);

}
