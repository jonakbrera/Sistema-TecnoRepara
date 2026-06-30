package com.tecnorepara.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LoggerUtil() {
    }

    public static void info(String mensaje) {
        imprimir("INFO", mensaje);
    }

    public static void error(String mensaje) {
        imprimir("ERROR", mensaje);
    }

    public static void error(String mensaje, Exception e) {
        imprimir("ERROR", mensaje + " | " + e.getMessage());
    }

    private static void imprimir(String tipo, String mensaje) {
        String fecha = LocalDateTime.now().format(FORMATTER);
        System.out.println("[" + fecha + "] [" + tipo + "] " + mensaje);
    }
}