package com.example.Backend_Api.repositories;

import org.springframework.stereotype.Repository;
import com.example.Backend_Api.entities.Local_arriendo;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface Local_repositorio extends JpaRepository<Local_arriendo, Integer>{

}
