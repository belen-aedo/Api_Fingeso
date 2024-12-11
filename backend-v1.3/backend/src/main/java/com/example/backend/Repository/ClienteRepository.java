package com.example.backend.Repository;

import com.example.backend.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByEmail(String email);
    Cliente findByRut(String rut);
    Cliente findById(long id);
    //Cliente findByNombre(String nombre);
    //Cliente findByApellido(String apellido);
    //Cliente findByTelefono(String telefono);
    //Cliente findByCorreo(String correo);
}

