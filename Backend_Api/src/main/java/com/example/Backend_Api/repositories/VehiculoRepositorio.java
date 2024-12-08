package com.example.Backend_Api.repositories;

import com.example.Backend_Api.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{

}
