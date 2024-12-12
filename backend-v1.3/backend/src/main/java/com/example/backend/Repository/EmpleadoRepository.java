package com.example.backend.Repository;

import com.example.backend.Entity.Empleado;

import java.util.List;

public interface EmpleadoRepository {
    public Empleado findById(long id);
    public Empleado findByEmail(String email);
    public Empleado findByPassword(String password);
    public Empleado findByrol(String rol);
    public void save(Empleado empleado);
}

