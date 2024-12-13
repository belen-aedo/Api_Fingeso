package com.example.backend.Repository;

import com.example.backend.Entity.Empleado;

import java.util.List;

public interface EmpleadoRepository {
    public Empleado findById(long id);
    public Empleado findByEmail(String email);
    public Empleado findByRut(String rut);
    public void save(Empleado empleado);
}

