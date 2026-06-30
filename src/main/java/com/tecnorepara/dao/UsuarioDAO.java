package com.tecnorepara.dao;

import com.tecnorepara.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO extends BaseDAO {

    public Usuario validarLogin(String usuario, String password) {
        String sql = """
                SELECT u.id_usuario, u.nombre, u.usuario, r.nombre_rol, u.estado
                FROM usuarios u
                INNER JOIN roles r ON u.id_rol = r.id_rol
                WHERE u.usuario = ?
                AND u.password = ?
                AND u.estado = true
                """;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("nombre_rol"),
                        rs.getBoolean("estado")
                );
            }

        } catch (Exception e) {
            com.tecnorepara.util.LoggerUtil.error("Error al validar login", e);
        }

        return null;
    }
}