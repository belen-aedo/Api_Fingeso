package com.example.Backend_Api.repositories;

import org.springframework.stereotype.Repository;
import com.example.Backend_Api.entities.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface Reservas_repositorio extends JpaRepository<Reservas, Long>{

}
