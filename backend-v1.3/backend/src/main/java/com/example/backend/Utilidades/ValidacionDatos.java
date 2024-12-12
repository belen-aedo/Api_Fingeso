package com.example.backend.Utilidades;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;


/**
 * Clase dedicada a la validación de ciertos datos para el registro de los usuarios-empleados y usuarios-clientes
 */

public class ValidacionDatos {

    // Getters and Setters
    /**
     * Dato con el cual se realizaran las validaciones de datos de tipo string
     */
    private String ValidarDatoString;

    /**
     * Dato con el cual se realizaran las validaciones de datos de tipo fecha
     */
    private LocalDate ValidarDatoFecha;

    // Constructor
    public ValidacionDatos(String ValidarDato) {
        this.ValidarDatoString = ValidarDato;
    }

    /**
     * Validar Número telefónico móvil Chileno (no teléfono fijo) String formato 56912345678
     *
     * @return True, False
     */
    public Boolean validarNumero() {
        // ValidarDato seria el número telefónico
        Pattern patron = Pattern.compile("^569\\d{8}$");
        Matcher matcher = patron.matcher(this.ValidarDatoString);
        return matcher.matches();
    }

    /**
     * Validar formato del correo electrónico
     *
     * @return True, False
     */
    public Boolean validarCorreo() {
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-za-z0-9-]+(\\.[A-za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // ValidarDato seria el correo
        Matcher matcher = patron.matcher(this.ValidarDatoString);
        return matcher.matches(); // Devuelve true si el correo coincide con el patrón y false en caso contrario.
    }

    /**
     * Contraseña valida si tiene al menos un numero, una mayuscula, una minuscula y del largo de 8 caracteres
     *
     * @return True o false según la contraseña, ejemplo 123456aA
     */
    public boolean validarPassword() {
        // Patrón regex para validar la contraseña con las condiciones especificadas
        Pattern patron = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8}$");
        Matcher matcher = patron.matcher(this.ValidarDatoString);
        // Devuelve true si la contraseña cumple con el patrón
        return matcher.matches();
    }

    /**
     * Validar formato del Rut, y que el Rut tenga sentido según leyes chilenas
     *
     * @return
     */
    public boolean validarRUT() {
        // Dividir el RUT en número y dígito verificador
        // ValidarDato seria el rut
        String[] partes = this.ValidarDatoString.split("-");
        if (partes.length != 2) {
            return false;
        }
        // Eliminar cualquier caracter no numérico
        String numeroRUT = partes[0].replaceAll("[^0-9]", "");
        String digitoVerificadorIngresado = partes[1].toUpperCase();
        // Validar que el número solo contenga dígitos
        if (!Pattern.matches("^\\d+$", numeroRUT)) {
            return false;
        }
        // Calcular el dígito verificador
        String digitoCalculado = calcularDigitoVerificador(numeroRUT);
        return digitoCalculado.equals(digitoVerificadorIngresado);
    }

    /**
     * Calcula el dígito verificador del Rut según los números anteriores
     *
     * @param numeroRUT Números anteriores al guion
     * @return digito verificador com un string
     */
    private String calcularDigitoVerificador(String numeroRUT) {
        int suma = 0;
        int factor = 2;
        // Recorrer los dígitos del rut de derecha a izquierda
        for (int i = numeroRUT.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(numeroRUT.charAt(i)) * factor;
            factor = (factor == 7) ? 2 : factor + 1;
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

    /**
     * Valida si la edad es mayor a 18 años
     *
     * @return True o False
     */
    public Boolean validarEdad() {
        LocalDate fechaActual = LocalDate.now(); //obtiene la fecha actual del Computador
        long EdadActual = ChronoUnit.YEARS.between(this.ValidarDatoFecha, fechaActual);
        return EdadActual >= 18;

    }

    public void setValidarDatoFecha(LocalDate CambiarFecha) {
        ValidarDatoFecha = CambiarFecha;

    }

    public void setValidarDatoString(String validarDatoString) {
        ValidarDatoString = validarDatoString;
    }


}
