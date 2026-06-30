package com.tecnorepara.config;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = AppConfig.class.getResourceAsStream("/config/application.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo application.properties");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar configuración: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String getDatabaseUrl() {
        return "jdbc:postgresql://"
                + get("db.host") + ":"
                + get("db.port") + "/"
                + get("db.name");
    }
}