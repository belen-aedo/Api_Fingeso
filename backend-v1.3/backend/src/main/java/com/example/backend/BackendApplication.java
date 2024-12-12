package com.example.backend;

import com.example.backend.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BackendApplication {
	// public class BackendApplication implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	/*
	//ejemplito
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		String sql = "INSERT INTO cliente (direccion, email, fecha_nacimiento, nombre, password, rut, telefono) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, "Calle Falsa 123", "juan.perez@example.com",  java.sql.Date.valueOf("1995-06-15"),
				"Juan Pérez", "password123", "12345678-9", "+56912345678");
		jdbcTemplate.update(sql, "Av. Siempreviva 742", "maria.gomez@example.com", java.sql.Date.valueOf("1995-06-15"),
				"María Gómez", "securepass456", "98765432-1", "+56987654321");
		jdbcTemplate.update(sql, "Pasaje Los Olivos 45", "pedro.lopez@example.com", java.sql.Date.valueOf("1995-06-15"),
				"Pedro López", "mypassword789", "19283746-5", "+56923456789");
		jdbcTemplate.update(sql, "Camino Real 654", "laura.martinez@example.com", java.sql.Date.valueOf("1995-06-15"),
				"Laura Martínez", "anotherpass321", "11223344-6", "+56934567890");
		jdbcTemplate.update(sql, "Plaza de Armas 101", "carlos.rojas@example.com", java.sql.Date.valueOf("1995-06-15"),
				"Carlos Rojas", "passwordabc", "55667788-0", "+56945678901");

		System.out.println("Datos insertados exitosamente");
	}
 */
}
