package com.example.backend.Repository;

import com.example.backend.Entity.Arriendo;

public interface ArriendoRepository {
    public Arriendo findArriendoByIdReserva(Long id_reserva);
    public void ChangePendingStatusById(Long id_arriendo, Boolean estado);
    public void ChangeState(Long id_arriendo, Boolean estado);
    public void updateCostWithPenalty(Long idReserva, Double multa);
}
