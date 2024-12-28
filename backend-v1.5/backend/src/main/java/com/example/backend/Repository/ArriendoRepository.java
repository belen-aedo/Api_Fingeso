package com.example.backend.Repository;

import com.example.backend.Entity.Arriendo;

public interface ArriendoRepository {
    public Arriendo findArriendoByIdReserva(Long id_reserva);
    public void CambiarEstadoPendiennteById(Long id_arriendo, Boolean estado);
    public void CambiarEstado(Long id_arriendo, Boolean estado);
}
