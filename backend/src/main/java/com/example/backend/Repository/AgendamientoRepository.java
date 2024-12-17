package com.example.backend.Repository;

import com.example.backend.Entity.Reserva;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.VehiculoReferencia;

public interface AgendamientoRepository {
    //solo una idea
    public void saveAgendaMiento(Reserva NewReserva, Sucursal sucursalRetiro, Sucursal sucursalDevolucion, VehiculoReferencia vehiculoReferencia);
}
