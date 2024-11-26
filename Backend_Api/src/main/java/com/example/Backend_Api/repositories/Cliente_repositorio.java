package com.example.Backend_Api.repositories;

import org.springframework.stereotype.Repository;
import com.example.Backend_Api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface Cliente_repositorio extends JpaRepository<Cliente, Integer>{

}
