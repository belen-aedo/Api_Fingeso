package com.example.backend.RowMappers;

import com.example.backend.Entity.Reserva;
import com.example.backend.Entity.Cliente;
import com.example.backend.Entity.Sucursal;
import com.example.backend.Entity.Arriendo;
import com.example.backend.Entity.VehiculoReferencia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservaRowMapper implements RowMapper<Reserva> {

    // Constantes para los nombres de columnas
    private static final String ID_RESERVA = "id_reserva";
    private static final String CLIENTE = "id_cliente";  // Asume que este es un campo que representa el cliente
    private static final String SUCURSAL_RETIRO = "id_sucursal_retiro";
    private static final String SUCURSAL_DEVOLUCION = "id_sucursal_devolucion";
    private static final String ARRIENDO = "id_arriendo";
    private static final String VEHICULO_REFERENCIA = "id_vehiculo_referencia";
    private static final String COSTO_TOTAL = "costo_total";
    private static final String FECHA_INICIO_RESERVA = "fecha_inicio_reserva";
    private static final String FECHA_TERMINO_RESERVA = "fecha_termino_reserva";
    private static final String FECHA_RESERVA = "fecha_reserva";
    private static final String RESERVA_FINALIZADA = "reserva_finalizada";
    private static final String PAGO_RESERVA = "pago_reserva";

    @Override
    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Reserva reserva = new Reserva();
        // Mapeamos las columnas a las propiedades de la entidad
        reserva.setId_reserva(rs.getLong("id_reserva"));

        return reserva;
    }
}