package com.tecnorepara.util;

import com.tecnorepara.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(
                AppConfig.getDatabaseUrl(),
                AppConfig.get("db.user"),
                AppConfig.get("db.password")
        );
    }
}