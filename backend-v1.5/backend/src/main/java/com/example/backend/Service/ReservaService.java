package com.example.backend.Service;


import com.example.backend.Entity.Reserva;
import com.example.backend.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    public Long GuardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }


    public Long BuscarReservaPorIdCliente(Long idCliente) {
        return reservaRepository.findReservaByIdCliente(idCliente);
    }

    public void borrarPorId(Long id) {
        reservaRepository.deleteById(id);
    }




}
