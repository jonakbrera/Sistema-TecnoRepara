package com.tecnorepara.validation;

public class ValidatorUtil {

    public static boolean estaVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean soloLetras(String texto) {
        return texto != null && texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }

    public static boolean soloNumeros(String texto) {
        return texto != null && texto.matches("\\d+");
    }

    public static boolean esEmailValido(String email) {
        if (estaVacio(email)) {
            return true;
        }
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean esDecimalValido(String texto) {
        return texto != null && texto.matches("\\d+(\\.\\d{1,2})?");
    }

    public static boolean longitudMaxima(String texto, int max) {
        return texto == null || texto.length() <= max;
    }
}