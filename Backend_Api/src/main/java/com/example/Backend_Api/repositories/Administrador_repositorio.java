package com.example.Backend_Api.repositories;


import org.springframework.stereotype.Repository;
import com.example.Backend_Api.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface Administrador_repositorio extends JpaRepository<Administrador, Integer>{

}
