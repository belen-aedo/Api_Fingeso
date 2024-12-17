-- este archivito servira para rellenar con datos la base de datos


--ejemplo de agendamiento-----------------------------------------------------------------------------------------------

--SUCURSALES
INSERT INTO sucursal (id_sucursal, ciudad_sucursal, nombre_sucursal, telefono_sucursal)
VALUES
    (1, 'Santiago', 'Sucursal Central', '123456789'),
    (2, 'Valparaíso', 'Sucursal Costa', '987654321');

--VEHICULO
INSERT INTO vehiculo (id, estado_vehiculo, kilometraje_vehiculo, year, id_sucursal, color_principal, marca, modelo, patente)
VALUES
    (1, 'D', 10000, 2020, 1, 'Rojo', 'Toyota', 'Corolla', 'ABC123'),
    (2, 'D', 5000, 2021, 1, 'Azul', 'Honda', 'Civic', 'DEF456'),
    (3, 'D', 20000, 2019, 2, 'Negro', 'Ford', 'Focus', 'GHI789');

INSERT INTO vehiculo (id, estado_vehiculo, kilometraje_vehiculo, year, id_sucursal, color_principal, marca, modelo, patente)
VALUES
    (4, 'D', 0, 2023, 1, 'Negro', 'Toyota', 'Corolla', 'GHI789');

--(1)-------------------------------------------------------------------------------------------------------------------

INSERT INTO agendamiento (id_agendamiento, fecha_inicio, fecha_finalizacion, proxima_fecha_disponible, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo)
VALUES
    (1, '2024-12-18', '2024-12-20', '2024-12-21', NULL, NULL, 1, 1, 1), -- Vehículo 1 reservado hasta el 20/12/2024
    (2, '2024-12-22', '2024-12-24', '2024-12-25', NULL, NULL, 1, 1, 1), -- Vehículo 1 reservado del 22 al 24/12/2024
    (3, '2024-12-20', '2024-12-23', '2024-12-24', NULL, NULL, 2, 2, 2); -- Vehículo 2 reservado del 20 al 23/12/2024

--PRUEBA 1

-- ENTRE EL 14 Y 16
SELECT v.*
FROM vehiculo v
         JOIN sucursal s ON v.id_sucursal = s.id_sucursal
         LEFT JOIN agendamiento a ON a.id_vehiculo = v.id
    AND (
                                         (a.fecha_inicio <= '2024-12-14' AND a.proxima_fecha_disponible >= '2024-12-14') OR
                                         (a.fecha_inicio <= '2024-12-16' AND a.proxima_fecha_disponible >= '2024-12-16') OR
                                         (a.fecha_inicio >= '2024-12-14' AND a.fecha_inicio <= '2024-12-16')
                                         )
WHERE s.nombre_sucursal = 'Sucursal Central'
  AND a.id_agendamiento IS NULL; -- Solo incluir vehículos sin reservas o fuera del rango

--(2)-------------------------------------------------------------------------------------------------------------------

DELETE FROM agendamiento; -- Limpia los datos previos para evitar duplicados

INSERT INTO agendamiento (id_agendamiento, fecha_inicio, fecha_finalizacion, proxima_fecha_disponible, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo)
VALUES
-- Reservas del Vehículo 1
(1, '2024-12-18', '2024-12-20', '2024-12-21', NULL, NULL, 1, 1, 1), -- Reservado hasta el 20/12/2024
(2, '2024-12-22', '2024-12-24', '2024-12-25', NULL, NULL, 1, 1, 1), -- Reservado del 22 al 24/12/2024

-- Reservas del Vehículo 3
(3, '2024-12-20', '2024-12-23', '2024-12-24', NULL, NULL, 2, 2, 3); -- Reservado del 20 al 23/12/2024

--por fechas del 26 al 30
SELECT v.*
FROM vehiculo v
         JOIN sucursal s ON v.id_sucursal = s.id_sucursal
         LEFT JOIN agendamiento a ON a.id_vehiculo = v.id
    AND (
                                         (a.fecha_inicio <= '2024-12-26' AND a.proxima_fecha_disponible >= '2024-12-26') OR
                                         (a.fecha_inicio <= '2024-12-30' AND a.proxima_fecha_disponible >= '2024-12-30') OR
                                         (a.fecha_inicio >= '2024-12-26' AND a.fecha_inicio <= '2024-12-30')
                                         )
WHERE s.nombre_sucursal = 'Sucursal Central'
  AND a.id_agendamiento IS NULL; -- Solo incluir vehículos sin reservas o fuera del rango

--(3)-------------------------------------------------------------------------------------------------------------------

DELETE FROM agendamiento; -- Limpia los datos previos para evitar conflictos

INSERT INTO agendamiento (id_agendamiento, fecha_inicio, fecha_finalizacion, proxima_fecha_disponible, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo)
VALUES
-- Reservas del Vehículo 1
(1, '2024-12-15', '2024-12-17', '2024-12-18', NULL, NULL, 1, 1, 1), -- Reservado del 15 al 17
(2, '2024-12-19', '2024-12-21', '2024-12-22', NULL, NULL, 1, 1, 1), -- Reservado del 19 al 21
(3, '2024-12-23', '2024-12-30', '2024-12-31', NULL, NULL, 1, 1, 1), -- Reservado del 23 al 30

-- Reservas del Vehículo 3 (por contexto)
(4, '2024-12-20', '2024-12-23', '2024-12-24', NULL, NULL, 2, 2, 3); -- Reservado del 20 al 23

--por fechas del 16 al 20
SELECT v.*
FROM vehiculo v
         JOIN sucursal s ON v.id_sucursal = s.id_sucursal
         LEFT JOIN agendamiento a ON a.id_vehiculo = v.id
    AND (
                                         (a.fecha_inicio <= '2024-12-16' AND a.proxima_fecha_disponible >= '2024-12-20') OR
                                         (a.fecha_inicio <= '2024-12-20' AND a.proxima_fecha_disponible >= '2024-12-20') OR
                                         (a.fecha_inicio >= '2024-12-16' AND a.fecha_inicio <= '2024-12-20')
                                         )
WHERE s.nombre_sucursal = 'Sucursal Central'
AND a.id_reserva IS NULL; -- Solo incluir vehículos sin reservas o fuera del rango

--(4)-VEHICULOS Y VEHICULOS REFRENCIA

-- Insertar en vehiculo_referencia (5 registros)
INSERT INTO public.vehiculo_referencia (
    cantidad_puertas,
    capacidad_pasajeros,
    costo_arriendo_vehiculo,
    costo_reserva_vehiculo,
    id_vehiculo_referencia,
    acriss,
    descripcion_publicacion,
    mecanico_automatico,
    modelo,
    tipo_combustible,
    url
) VALUES
      (4, 5, 120000.00, 60000.00, 1, 'LCAR', 'Vehículo 4x4 ideal para terrenos difíciles.', 'Automático', 'Toyota Land Cruiser', 'Gasolina', 'https://drive.google.com/file/d/1TeDws71mnhMre1sKw4UFdvYKmla57CpT/view?usp=drive_link'),
      (4, 5, 150000.00, 75000.00, 2, 'LCAR', 'SUV todo terreno con gran capacidad.', 'Automático', 'Jeep Wrangler', 'Gasolina', 'http://example.com/jeep_wrangler.jpg'),
      (4, 5, 130000.00, 65000.00,  3, 'LCAR', 'Ideal para aventuras off-road.', 'Automático', 'Ford Bronco', 'Gasolina', 'http://example.com/ford_bronco.jpg'),
      (4, 5, 140000.00, 70000.00, 4, 'LCAR', 'Vehículo con gran tracción para todo tipo de terreno.', 'Automático', 'Land Rover Defender', 'Diésel', 'http://example.com/land_rover_defender.jpg'),
      (4, 5, 160000.00, 80000.00, 5, 'LCAR', 'SUV de lujo con capacidad para 5 personas.', 'Automático', 'Mercedes-Benz G-Class', 'Gasolina', 'http://example.com/mercedes_g_class.jpg');


-- Insertar en vehiculo (7 registros ajustados)
INSERT INTO public.vehiculo (
    estado_vehiculo,
    kilometraje_vehiculo,
    year,
    id,
    id_sucursal,
    color_principal,
    marca,
    modelo,
    patente
) VALUES
      ('A', 20000.00, 2022, 1, 1, 'Negro', 'Toyota', 'Toyota Land Cruiser', 'ABC1234'),
      ('A', 15000.00, 2023, 2, 1, 'Blanco', 'Jeep', 'Jeep Wrangler', 'XYZ5678'),
      ('A', 10000.00, 2021, 3, 1, 'Rojo', 'Ford', 'Ford Bronco', 'LMN2345'),
      ('A', 30000.00, 2020, 4, 1, 'Verde', 'Land Rover', 'Land Rover Defender', 'DEF7890'),
      ('A', 5000.00, 2023, 5, 1, 'Azul', 'Mercedes-Benz', 'Mercedes-Benz G-Class', 'GHI5678'),
      ('A', 25000.00, 2022, 6, 2, 'Amarillo', 'Toyota', 'Toyota Land Cruiser', 'JKL2345'),
      ('A', 10000.00, 2023, 7, 2, 'Blanco', 'Jeep', 'Jeep Wrangler', 'MNO3456');



























