package com.example.backend;

import com.example.backend.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Insertar clientes
		String sqlCliente = "INSERT INTO cliente (direccion, email, fecha_nacimiento, nombre, password, rut, telefono, id_cliente) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sqlCliente, "Calle Falsa 123", "juan.perez@example.com", java.sql.Date.valueOf("1995-06-15"),
				"Juan Pérez", "password123", "12345678-9", "+56912345678", 1);
		jdbcTemplate.update(sqlCliente, "Av. Siempreviva 742", "maria.gomez@example.com", java.sql.Date.valueOf("1995-06-15"),
				"María Gómez", "securepass456", "98765432-1", "+56987654321", 2);

		// Insertar sucursales
		String sqlSucursal = "INSERT INTO sucursal (id_sucursal, ciudad_sucursal, nombre_sucursal, telefono_sucursal) " +
				"VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sqlSucursal, 1, "Santiago", "Sucursal Central", "123456789");
		jdbcTemplate.update(sqlSucursal, 2, "Valparaíso", "Sucursal Costa", "987654321");

		// Insertar vehículos de referencia
		String sqlVehiculoReferencia = "INSERT INTO vehiculo_referencia " +
				"(cantidad_puertas, capacidad_pasajeros, costo_arriendo_vehiculo, costo_reserva_vehiculo, " +
				"id_vehiculo_referencia, acriss, descripcion_publicacion, mecanico_automatico, modelo, tipo_combustible, url) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sqlVehiculoReferencia, 4, 5, 120000.00, 60000.00, 1, "LCAR",
				"Vehículo 4x4 ideal para terrenos difíciles.", "Automático", "Toyota Land Cruiser", "Gasolina",
				"https://drive.google.com/file/d/1TeDws71mnhMre1sKw4UFdvYKmla57CpT/view?usp=drive_link");

		jdbcTemplate.update(sqlVehiculoReferencia, 4, 5, 150000.00, 75000.00, 2, "LCAR",
				"SUV todo terreno con gran capacidad.", "Automático", "Jeep Wrangler", "Gasolina",
				"http://example.com/jeep_wrangler.jpg");

		/// nuevos añadidos -------

		jdbcTemplate.update(sqlVehiculoReferencia,4, 5, 130000.00, 65000.00,  3,
				"LCAR", "Ideal para aventuras off-road.", "Automático", "Ford Bronco", "Gasolina", "http://example.com/ford_bronco.jpg");

		jdbcTemplate.update(sqlVehiculoReferencia, 4, 5, 140000.00, 70000.00, 4, "LCAR",
				"Vehículo con gran tracción para todo tipo de terreno.", "Automático", "Land Rover Defender", "Diésel", "http://example.com/land_rover_defender.jpg");


		jdbcTemplate.update(sqlVehiculoReferencia, 4, 5, 160000.00, 80000.00, 5, "LCAR",
				"SUV de lujo con capacidad para 5 personas.", "Automático", "Mercedes-Benz G-Class", "Gasolina", "http://example.com/mercedes_g_class.jpg");

		// Insertar vehículos individuales con estados "D" o "M"
		String sqlVehiculo = "INSERT INTO vehiculo " +
				"(estado_vehiculo, kilometraje_vehiculo, year, id, id_sucursal, color_principal, marca, modelo, patente) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sqlVehiculo, "D", 20000.00, 2022, 1, 1, "Negro", "Toyota", "Toyota Land Cruiser", "ABC1234");
		jdbcTemplate.update(sqlVehiculo, "D", 15000.00, 2023, 2, 1, "Blanco", "Jeep", "Jeep Wrangler", "XYZ5678");
		jdbcTemplate.update(sqlVehiculo, "D", 10000.00, 2021, 3, 1, "Rojo", "Ford", "Ford Bronco", "LMN2345");
		jdbcTemplate.update(sqlVehiculo, "D", 30000.00, 2020, 4, 1, "Verde", "Land Rover", "Land Rover Defender", "DEF7890");
		jdbcTemplate.update(sqlVehiculo, "D", 5000.00, 2023, 5, 1, "Azul", "Mercedes-Benz", "Mercedes-Benz G-Class", "GHI5678");
		jdbcTemplate.update(sqlVehiculo, "D", 25000.00, 2022, 6, 2, "Amarillo", "Toyota", "Toyota Land Cruiser", "JKL2345");
		jdbcTemplate.update(sqlVehiculo, "D", 10000.00, 2023, 7, 2, "Blanco", "Jeep", "Jeep Wrangler", "MNO3456");


		// Insertar reservas con agendamiento y arriendo vinculado
		String sqlReserva = "INSERT INTO reservas (id_cliente, id_sucursal_retiro, id_sucursal_devolucion, id_vehiculo_referencia, " +
				"costo_total, fecha_inicio_reserva, fecha_termino_reserva, fecha_reserva, reserva_finalizada, pago_reserva) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// Insertar reservas para los clientes existentes
		//cliente 1 reserva 1
		jdbcTemplate.update(sqlReserva, 1, 1, 2, 1, 60000.00, java.sql.Date.valueOf("2025-01-04"), java.sql.Date.valueOf("2025-01-06"),
				java.sql.Date.valueOf("2025-12-29"), true, true);

		//cliente 2 reserva 2
		jdbcTemplate.update(sqlReserva, 2, 1, 1, 2, 75000.00, java.sql.Date.valueOf("2025-01-04"), java.sql.Date.valueOf("2024-01-07"),
				java.sql.Date.valueOf("2024-12-29"), true, false);

		//cliente 1 reserva 3
		jdbcTemplate.update(sqlReserva, 1, 1, 2, 3, 65000.00, java.sql.Date.valueOf("2025-01-06"), java.sql.Date.valueOf("2025-01-07"),
				java.sql.Date.valueOf("2024-12-29"), true, true);

		//cliente 2 reserva 4
		jdbcTemplate.update(sqlReserva, 2, 1, 1, 4, 70000.00, java.sql.Date.valueOf("2025-01-08"), java.sql.Date.valueOf("2025-01-09"),
				java.sql.Date.valueOf("2024-12-29"), true, false);

		// Insertar agendamientos
		String sqlAgendamiento = "INSERT INTO agendamiento (fecha_finalizacion, fecha_inicio, proxima_fecha_disponible, id_agendamiento, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// Agendamiento basado en las reservas y vehículos
		// reserva 1 cliente 1
		jdbcTemplate.update(sqlAgendamiento, java.sql.Date.valueOf("2025-01-06"), java.sql.Date.valueOf("2025-01-04"),
				java.sql.Date.valueOf("2025-01-07"), 1, 1, 1, 2, 1, 1);

		// reserva 2 cliente 2
		jdbcTemplate.update(sqlAgendamiento, java.sql.Date.valueOf("2025-01-07"), java.sql.Date.valueOf("2025-01-04"),
				java.sql.Date.valueOf("2025-01-08"), 2, 2, 2, 1, 1, 2);

		//reserva 4 cliente 2
		jdbcTemplate.update(sqlAgendamiento, java.sql.Date.valueOf("2025-01-09"), java.sql.Date.valueOf("2025-01-04"),
				java.sql.Date.valueOf("2025-01-10"), 3, 2, 4, 1, 1, 4);

		//reserva 3 cliente 1
		jdbcTemplate.update(sqlAgendamiento, java.sql.Date.valueOf("2025-01-07"), java.sql.Date.valueOf("2024-01-06"),
				java.sql.Date.valueOf("2025-01-08"), 4, 1, 3, 2, 1, 3);


		// Insertar arriendos
		String sqlArriendo = "INSERT INTO arriendo (costo_total, estado_arriendo, fecha_arriendo, fecha_inicio_arriendo, fecha_termino_arriendo, " +
				"pendiente_arriendo, id_arriendo, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo, rut_cliente) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// Arriendo para la reserva 1 (Cliente 1)
		jdbcTemplate.update(sqlArriendo, 60000.00, true, java.sql.Date.valueOf("2024-12-31"),
				java.sql.Date.valueOf("2025-01-04"), java.sql.Date.valueOf("2025-01-06"), false, 1, 1, 2, 1, 1, "12345678-9");

		// Arriendo para la reserva 2 (Cliente 2)
		jdbcTemplate.update(sqlArriendo, 75000.00, true, java.sql.Date.valueOf("2024-12-31"),
				java.sql.Date.valueOf("2025-01-04"), java.sql.Date.valueOf("2024-01-07"), false, 2, 2, 1, 1, 2, "98765432-1");

		// Arriendo para la reserva 3 (Cliente 1)
		jdbcTemplate.update(sqlArriendo, 65000.00, true, java.sql.Date.valueOf("2024-12-31"),
				java.sql.Date.valueOf("2025-01-06"), java.sql.Date.valueOf("2025-01-07"), false, 3, 3, 1, 1, 3, "12345678-9");

		// Arriendo para la reserva 4 (Cliente 2)
		jdbcTemplate.update(sqlArriendo, 70000.00, true, java.sql.Date.valueOf("2024-12-31"),
				java.sql.Date.valueOf("2025-01-04"), java.sql.Date.valueOf("2025-01-09"), false, 4, 4, 1, 1, 4, "98765432-1");

		System.out.println("Datos de clientes, sucursales, vehículos, reservas y agendamientos insertados exitosamente.");
		System.out.println("Datos de arriendos insertados exitosamente.");
	}

}

/*

INSERT INTO public.arriendo(
		costo_total, estado_arriendo, fecha_arriendo, fecha_inicio_arriendo, fecha_termino_arriendo, pendiente_arriendo, id_arriendo, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo, rut_cliente)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

*/


