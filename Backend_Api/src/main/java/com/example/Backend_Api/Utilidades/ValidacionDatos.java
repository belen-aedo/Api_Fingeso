package com.example.Backend_Api.Utilidades;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase dedicada a la validación de ciertos datos para el regsitro de los usuarios-empleados y usuarios-clientes
 */
public class ValidacionDatos {

    /**
     * Dato con el cual se realizaran las validaciones
     */
    private String ValidarDato;

    // Constructor
    public ValidacionDatos(String ValidarDato){
        this.ValidarDato = ValidarDato;
    }

    // Getters and Setters
    public void setValidarDato(String CambiarDato) {
        this.ValidarDato = CambiarDato;
    }

    /**
     * Validar Número telefónico móvil Chileno (no teléfono fijo)
     * @param numeroTelefonico String formato 56912345678
     * @return True, False
     */
    public Boolean validarNumero() {
        // ValidarDato seria el número telefónico
        Pattern patron = Pattern.compile("^569\\d{8}$");
        Matcher matcher = patron.matcher(this.ValidarDato);
        return matcher.matches();
    }

    /**
     * Validar formato del correo electrónico
     * @param correo String formato ejemplo@dominio.com
     * @return True, False
     */
    public Boolean validarCorreo() {
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-za-z0-9-]+(\\.[A-za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // ValidarDato seria el correo
        Matcher matcher = patron.matcher(this.ValidarDato);
        return matcher.matches(); // Devuelve true si el correo coincide con el patrón y false en caso contrario.
    }

    /**
     * Validar formato del Rut, y que el Rut tenga sentido según leyes chilenas
     * @param rut_registrado
     * @return
     */
    public boolean validarRUT() {
            // Dividir el RUT en número y dígito verificador
            // ValidarDato seria el rut
            String[] partes = this.ValidarDato.split("-");
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

}
