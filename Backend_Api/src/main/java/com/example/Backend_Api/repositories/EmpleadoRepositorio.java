package com.example.Backend_Api.repositories;

import com.example.Backend_Api.entities.Empleado;
import com.example.Backend_Api.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {

    Empleado findByCorreoElectronico(String correoElectronico);
    Empleado findByRut(String rutEmpleado);
    Empleado findByrol(String rol);
    boolean sucursalExistente(Sucursal sucursal);
    Empleado save(Empleado empleado);

}
