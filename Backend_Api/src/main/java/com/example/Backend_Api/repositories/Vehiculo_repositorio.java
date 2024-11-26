package com.example.Backend_Api.repositories;

import com.example.Backend_Api.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Vehiculo_repositorio extends JpaRepository<Vehiculo, Long> {

}
