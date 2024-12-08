package com.example.Backend_Api.repositories;

import com.example.Backend_Api.entities.Usuario_registrado;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface Cliente_repositorio extends JpaRepository<Usuario_registrado, Long> {
    Usuario_registrado findByCorreo_registrado(String correo_registrado);
}
