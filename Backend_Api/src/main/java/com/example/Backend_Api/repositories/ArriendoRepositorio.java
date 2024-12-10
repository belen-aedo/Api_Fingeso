package com.example.Backend_Api.repositories;

import com.example.Backend_Api.entities.Arriendo;
import com.example.Backend_Api.entities.Cliente;
import com.example.Backend_Api.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface ArriendoRepositorio extends JpaRepository<Arriendo, Long> {
    Arriendo findByfecha_inicio_arriendo(LocalDate fecha_inicio_arriendo);
    Arriendo findByfecha_termino_arriendo(LocalDate fecha_termino_arriendo);
    Arriendo findBycliente(Cliente cliente);
    Arriendo findByvehiculo (Vehiculo vehiculo);
    Arriendo findByid_arriendo(long id_sucursal);
    Arriendo save (Arriendo arriendo);
}
