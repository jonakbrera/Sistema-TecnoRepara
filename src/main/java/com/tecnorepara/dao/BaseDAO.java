package com.tecnorepara.dao;

import com.tecnorepara.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO {

    protected Connection getConnection() throws SQLException {
        return ConexionBD.obtenerConexion();
    }
}