package com.example.backend.Controller;
import com.example.backend.Entity.Empleado;
import com.example.backend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleado")
@CrossOrigin
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/registrar")
    public String registrarEmpleado(@RequestBody Empleado nuevoEmpleado) {
        try {
            empleadoService.registrarEmpleado(
                    nuevoEmpleado.getNombre(),
                    nuevoEmpleado.getRut(),
                    nuevoEmpleado.getTelefono(),
                    nuevoEmpleado.getRol(),
                    nuevoEmpleado.getSucursal().getNombreSucursal(),
                    nuevoEmpleado.getEmail(),
                    nuevoEmpleado.getPassword()
            );
            return "Empleado registrado con exitosamente.";
        } catch (Exception e) {
            return "Error al registrar empleado: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String loginEmpleado(@RequestBody Empleado empleadoR) {
            return empleadoService.login(empleadoR.getEmail(), empleadoR.getPassword());
    }

}


/*
ejemplo
{
    "nombre": "Juan Perez",
    "rut": "20722210-0",
    "telefono": "56996663811",
    "rol": "asalariado",
    "email": "juan.perez@example.com",
    "password": "2lol12MN",
    "sucursal": { "nombreSucursal": "Sucursal Central"}
}
 */