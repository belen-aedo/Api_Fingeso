package com.example.Backend_Api.repositories;

import org.springframework.stereotype.Repository;
import com.example.Backend_Api.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface Reserva_repositorio extends JpaRepository<Reserva, Long>{

}
