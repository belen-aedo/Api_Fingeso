package com.example.backend.Entity;

import jakarta.persistence.*;

import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cliente", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rut"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente; // Identificador

    @Column(nullable = false, unique = true)
    private String rut; // DNI del cliente

    @Column(nullable = false)
    private String nombre; // Nombre del cliente

    private String direccion; // Dirección del cliente

    @Column(nullable = false)
    private String telefono; // Teléfono del cliente

    @Column(nullable = false, unique = true)
    private String email; // Correo electrónico del cliente

    @Column(nullable = false)
    private String password; // Contraseña del cliente

    private LocalDate fechaNacimiento; // Fecha de nacimiento del cliente

    /* -mappedBy = "cliente": Indica que esta relación es mantenida por el atributo "cliente" en la clase Reserva.
    cascade = CascadeType.ALL: Propaga operaciones como persist, merge, y remove desde cliente a sus reservas.
    orphanRemoval = true: Elimina automáticamente las reservas que se eliminan de la lista. */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reserva> reservas; //Clientes-1------M-Reservas

    // No-arg constructor
    public Cliente() { }

    //Constructores
    public Cliente(String rut, String nombre, String direccion, String telefono, String email, String password, LocalDate fechaNacimiento) {
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.reservas = new HashSet<>();
    }

    //Getters
    public String getRut() {
        return rut;
    }
    public String getNombre() {

        return nombre;
    }

    public String getDireccion() {

        return direccion;
    }

    public String getTelefono() {

        return telefono;
    }

    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
    }

    public LocalDate getFechaNacimiento() {

        return fechaNacimiento;
    }

    // Setters
    public void setIdCliente(long id_usu_registrado) {
        this.idCliente = id_usu_registrado;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String correo) {
        this.email = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setId(long id) {this.idCliente = id;}

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }
}
