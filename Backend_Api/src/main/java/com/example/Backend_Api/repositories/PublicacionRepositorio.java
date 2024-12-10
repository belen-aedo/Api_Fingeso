package com.example.Backend_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Backend_Api.entities.VehiculoReferencia;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepositorio extends JpaRepository<VehiculoReferencia, Long>{
    VehiculoReferencia findById_publicacion(long id_publicacion);
    VehiculoReferencia findByDescripcion_publicacion(String descripcion_publicacion);
}
