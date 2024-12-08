package com.example.Backend_Api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Table(name = "Cliente")
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usu_registrado; // Identificador
    private String rut_registrado; // DNI del cliente
    private String nombre_registrado; // Nombre del cliente
    private String direccion_registrado; // Dirección del cliente
    private int telefono_registrado; // Teléfono del cliente
    private String correo_registrado; // Correo electrónico del cliente
    private String password; // Contraseña del cliente
    private LocalDate fecha_nacimiento_cliente; // Fecha de nacimiento del cliente

    //Constructores


    public Cliente(long id_usu_registrado, String rut_registrado, String nombre_registrado, String apellido_registrado, String direccion_registrado, int telefono_registrado, String correo_registrado, String password, LocalDate fecha_nacimiento_cliente) {
        this.id_usu_registrado = id_usu_registrado;
        this.rut_registrado = rut_registrado;
        this.nombre_registrado = nombre_registrado;
        this.direccion_registrado = direccion_registrado;
        this.telefono_registrado = telefono_registrado;
        this.correo_registrado = correo_registrado;
        this.password = password;
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
    }

    public Cliente() {

    }

    //Getters y Setters
    public long getId_usu_registrado() {return id_usu_registrado;}

    public void setId_usu_registrado(long id_usu_registrado) {
        this.id_usu_registrado = id_usu_registrado;
    }

    public String getRut_registrado() {
        return rut_registrado;
    }

    public void setRut_registrado(String rut_registrado) {
        this.rut_registrado = rut_registrado;
    }

    public String getNombre_registrado() {
        return nombre_registrado;
    }

    public void setNombre_registrado(String nombre_registrado) {
        this.nombre_registrado = nombre_registrado;
    }

    public String getDireccion_registrado() {
        return direccion_registrado;
    }

    public void setDireccion_registrado(String direccion_registrado) {
        this.direccion_registrado = direccion_registrado;
    }

    public int getTelefono_registrado() {
        return telefono_registrado;
    }

    public void setTelefono_registrado(int telefono_registrado) {
        this.telefono_registrado = telefono_registrado;
    }

    public String getCorreo_registrado() {
        return correo_registrado;
    }

    public void setCorreo_registrado(String correo_registrado) {
        this.correo_registrado = correo_registrado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contrasena_registrado) {
        this.password = contrasena_registrado;
    }

    public LocalDate getFecha_nacimiento_cliente() {
        return fecha_nacimiento_cliente;
    }

    public void setFecha_nacimiento_cliente(LocalDate fecha_nacimiento_cliente) {
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
    }

    // Validar rut
    public boolean validarRUT() {
        try {
            // Dividir el RUT en número y dígito verificador
            String[] partes = rut_registrado.split("-");
            if (partes.length != 2) {
                return false;
            }
            String numeroRUT = partes[0].replaceAll("[^0-9]", ""); // Eliminar cualquier caracter no numérico
            String digitoVerificadorIngresado = partes[1].toUpperCase();
            // Validar que el número solo contenga dígitos
            if (!Pattern.matches("^\\d+$", numeroRUT)) {
                return false;
            }
            // Calcular el dígito verificador
            String digitoCalculado = calcularDigitoVerificador(numeroRUT);
            // Comparar el dígito calculado con el ingresado
            return digitoCalculado.equals(digitoVerificadorIngresado);

        } catch (Exception e) {
            return false;
        }
    }
    // Método para calcular el dígito verificador de un RUT usando el algoritmo módulo 11.
    private String calcularDigitoVerificador(String numeroRUT) {
        int suma = 0;
        int factor = 2;

        // Recorrer los dígitos del RUT de derecha a izquierda
        for (int i = numeroRUT.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(numeroRUT.charAt(i)) * factor;
            factor = (factor == 7) ? 2 : factor + 1; // Ciclar los factores 2,3,4,5,6,7
        }
        int mod = 11 - (suma % 11);
        if (mod == 11) {
            return "0";
        } else if (mod == 10) {
            return "K";
        } else {
            return String.valueOf(mod);
        }
    }


}