-- este archivito servira para rellenar con datos la base de datos


--(1)----- fechas para el 6 de enero (presentación)

-- Insertar clientes
INSERT INTO cliente (direccion, email, fecha_nacimiento, nombre, password, rut, telefono)
VALUES
    ('Calle Falsa 123', 'juan.perez@example.com', '1995-06-15', 'Juan Pérez', 'Passwor1', '12345678-9', '56912345678'),
    ('Av. Siempreviva 742', 'maria.gomez@example.com', '1995-06-15', 'María Gómez', 'Secur456', '98765432-1', '56987654321');

-- Insertar sucursales
INSERT INTO sucursal (id_sucursal, ciudad_sucursal, nombre_sucursal, telefono_sucursal)
VALUES
    (1, 'Santiago', 'Sucursal Central', '123456789'),
    (2, 'Valparaíso', 'Sucursal Costa', '987654321');

-- Insertar vehículos de referencia
INSERT INTO vehiculo_referencia (cantidad_puertas, capacidad_pasajeros, costo_arriendo_vehiculo, costo_reserva_vehiculo, id_vehiculo_referencia, acriss, descripcion_publicacion, mecanico_automatico, modelo, tipo_combustible, url)
VALUES
    (4, 5, 120000.00, 60000.00, 1, 'LCAR', 'Vehículo 4x4 ideal para terrenos difíciles.', 'Automático', 'Toyota Land Cruiser', 'Gasolina', 'https://drive.google.com/file/d/1TeDws71mnhMre1sKw4UFdvYKmla57CpT/view?usp=drive_link'),
    (4, 5, 150000.00, 75000.00, 2, 'LCAR', 'SUV todo terreno con gran capacidad.', 'Automático', 'Jeep Wrangler', 'Gasolina', 'http://example.com/jeep_wrangler.jpg'),
    (4, 5, 130000.00, 65000.00, 3, 'LCAR', 'Ideal para aventuras off-road.', 'Automático', 'Ford Bronco', 'Gasolina', 'http://example.com/ford_bronco.jpg'),
    (4, 5, 140000.00, 70000.00, 4, 'LCAR', 'Vehículo con gran tracción para todo tipo de terreno.', 'Automático', 'Land Rover Defender', 'Diésel', 'http://example.com/land_rover_defender.jpg'),
    (4, 5, 160000.00, 80000.00, 5, 'LCAR', 'SUV de lujo con capacidad para 5 personas.', 'Automático', 'Mercedes-Benz G-Class', 'Gasolina', 'http://example.com/mercedes_g_class.jpg');

-- Insertar vehículos individuales
INSERT INTO vehiculo (estado_vehiculo, kilometraje_vehiculo, year, id, id_sucursal, color_principal, marca, modelo, patente)
VALUES
    ('D', 20000.00, 2022, 1, 1, 'Negro', 'Toyota', 'Toyota Land Cruiser', 'ABC1234'),
    ('D', 15000.00, 2023, 2, 1, 'Blanco', 'Jeep', 'Jeep Wrangler', 'XYZ5678'),
    ('D', 10000.00, 2021, 3, 1, 'Rojo', 'Ford', 'Ford Bronco', 'LMN2345'),
    ('D', 30000.00, 2020, 4, 1, 'Verde', 'Land Rover', 'Land Rover Defender', 'DEF7890'),
    ('D', 5000.00, 2023, 5, 1, 'Azul', 'Mercedes-Benz', 'Mercedes-Benz G-Class', 'GHI5678'),
    ('D', 25000.00, 2022, 6, 2, 'Amarillo', 'Toyota', 'Toyota Land Cruiser', 'JKL2345'),
    ('D', 10000.00, 2023, 7, 2, 'Blanco', 'Jeep', 'Jeep Wrangler', 'MNO3456');

-- Insertar reservas
INSERT INTO reservas (id_cliente, id_sucursal_retiro, id_sucursal_devolucion, id_vehiculo_referencia, costo_total, fecha_inicio_reserva, fecha_termino_reserva, fecha_reserva, reserva_finalizada, pago_reserva)
VALUES
    (1, 1, 2, 1, 60000.00, '2025-01-04', '2025-01-06', '2025-12-29', true, true),
    (2, 1, 1, 2, 75000.00, '2025-01-04', '2024-01-07', '2024-12-29', true, false),
    (1, 1, 2, 3, 65000.00, '2025-01-07', '2025-01-08', '2024-12-29', true, true),
    (2, 1, 1, 4, 70000.00, '2025-01-08', '2025-01-09', '2024-12-29', true, false);

-- Insertar agendamientos
INSERT INTO agendamiento (fecha_finalizacion, fecha_inicio, proxima_fecha_disponible, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo)
VALUES
    ('2025-01-06', '2025-01-04', '2025-01-07', 1, 1, 2, 1, 1),
    ('2025-01-07', '2025-01-04', '2025-01-08', 2, 2, 1, 1, 2),
    ('2025-01-09', '2025-01-08', '2025-01-10', 2, 4, 1, 1, 4),
    ('2025-01-08', '2025-01-07', '2025-01-09', 1, 3, 2, 1, 3);

-- Insertar arriendos
INSERT INTO arriendo (costo_total, estado_arriendo, fecha_arriendo, fecha_inicio_arriendo, fecha_termino_arriendo, pendiente_arriendo, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo, rut_cliente)
VALUES
    (120000.00, true, '2024-12-31', '2025-01-04', '2025-01-06', false, 1, 2, 1, 1, '12345678-9'),
    (150000.00, true, '2024-12-31', '2025-01-04', '2024-01-07', false, 2, 1, 1, 2, '98765432-1'),
    (130000.00, true, '2024-12-31', '2025-01-07', '2025-01-08', false, 3, 1, 1, 3, '12345678-9'),
    (140000.00, true, '2024-12-31', '2025-01-08', '2025-01-09', false, 4, 1, 1, 4, '98765432-1');


























