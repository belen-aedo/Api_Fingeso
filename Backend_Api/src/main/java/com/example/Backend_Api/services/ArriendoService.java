package com.example.Backend_Api.services;

import com.example.Backend_Api.entities.Arriendo;
import com.example.Backend_Api.entities.Cliente;
import com.example.Backend_Api.entities.Vehiculo;
import java.time.LocalDate;
import com.example.Backend_Api.repositories.ArriendoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArriendoService {

    @Autowired
    private ArriendoRepositorio arriendoRepo;

    //Encontrar repositorio por fecha de inicio de arriendo
    public Arriendo findByfecha_inicio_arriendo(LocalDate fecha_inicio_arriendo) {
        return arriendoRepo.findByfecha_inicio_arriendo(fecha_inicio_arriendo);
    }

    public Arriendo findByfecha_termino_arriendo(LocalDate fecha_termino_arriendo){
        return arriendoRepo.findByfecha_termino_arriendo(fecha_termino_arriendo);
    }
    public Arriendo findBycliente(Cliente cliente){
        return arriendoRepo.findBycliente(cliente);
    }
    public Arriendo findByvehiculo(Vehiculo vehiculo){
        return arriendoRepo.findByvehiculo(vehiculo);
    }
    public Arriendo findByid_arriendo(long id_sucursal){
        return arriendoRepo.findByid_arriendo(id_sucursal);
    }
}

