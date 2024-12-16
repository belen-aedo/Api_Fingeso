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

INSERT INTO agendamiento (id, fecha_inicio, fecha_finalizacion, proxima_fecha_disponible, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo)
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
  AND a.id IS NULL; -- Solo incluir vehículos sin reservas o fuera del rango

--(2)-------------------------------------------------------------------------------------------------------------------

DELETE FROM agendamiento; -- Limpia los datos previos para evitar duplicados

INSERT INTO agendamiento (id, fecha_inicio, fecha_finalizacion, proxima_fecha_disponible, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo)
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
  AND a.id IS NULL; -- Solo incluir vehículos sin reservas o fuera del rango

--(3)-------------------------------------------------------------------------------------------------------------------

DELETE FROM agendamiento; -- Limpia los datos previos para evitar conflictos

INSERT INTO agendamiento (id, fecha_inicio, fecha_finalizacion, proxima_fecha_disponible, id_cliente, id_reserva, id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo)
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
  AND a.id IS NULL; -- Solo incluir vehículos sin reservas o fuera del rango
