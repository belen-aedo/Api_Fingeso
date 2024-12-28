package com.example.backend.Repository;

import com.example.backend.Entity.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository {

    public Reserva findById(int id);
    public Reserva findReservaByDate(LocalDate date);
    public Long save (Reserva reserva);
    public List<Reserva> findByBetwenDates(LocalDate From, LocalDate To);
    public Long findReservaByIdCliente(Long clienteId);
    public void deleteById(Long id);

}
