-- este archivito servira para rellenar con datos la base de datos

SELECT *
FROM Cliente

SELECT *
FROM sucursal

SELECT *
FROM vehiculo

    INSERT INTO public.vehiculo (
    kilometraje_vehiculo, year, id_reserva, id_sucursal, color_principal, estado_vehiculo, marca, modelo, patente
) VALUES
    (150000, 2005, NULL, 1, 'Rojo', 'Disponible', 'Toyota', 'Corolla', 'ABC123'),
    (120000, 2010, NULL, 2, 'Azul', 'En mantenimiento', 'Honda', 'Civic', 'XYZ456'),
    (80000, 2018, NULL, 3, 'Negro', 'Disponible', 'Ford', 'Focus', 'DEF789'),
    (200000, 2000, NULL, 1, 'Blanco', 'Disponible', 'Chevrolet', 'Sail', 'GHI012');

INSERT INTO public.sucursal (
    ciudad_sucursal, nombre_sucursal, telefono_sucursal
) VALUES
      ('Santiago', 'Sucursal Alameda', '22334455'),
      ('Valparaíso', 'Sucursal Viña', '22445566'),
      ('Concepción', 'Sucursal Concepción', '22336677'),
      ('La Serena', 'Sucursal La Serena', '22338899');

DELETE
FROM vehiculo

ALTER SEQUENCE vehiculo_id_seq RESTART WITH 1;


INSERT INTO public.reservas(
    costo_total, fecha_inicio_reserva, fecha_reserva, fecha_termino_reserva,
    pago_reserva, reserva_finalizada, id_arriendo, id_cliente,
    id_sucursal_devolucion, id_sucursal_retiro, id_vehiculo_referencia)
VALUES
    (15000.00, '2024-12-15', '2024-12-10', '2024-12-20',
     true, false, NULL, NULL,
     NULL, NULL, NULL),
    (30000.00, '2024-12-18', '2024-12-12', '2024-12-25',
     false, true, NULL, NULL,
     NULL, NULL, NULL),
    (45000.00, '2024-12-20', '2024-12-14', '2024-12-30',
     true, false, NULL, NULL,
     NULL, NULL, NULL),
    (50000.00, '2024-12-22', '2024-12-16', '2024-12-31',
     false, true, NULL, NULL,
     NULL, NULL, NULL);
