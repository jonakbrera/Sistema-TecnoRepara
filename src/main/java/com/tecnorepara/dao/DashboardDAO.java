package com.tecnorepara.dao;

import com.tecnorepara.dto.DashboardResumen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO extends BaseDAO {

    public DashboardResumen obtenerResumen() {
        int clientes = contar("SELECT COUNT(*) FROM clientes WHERE estado = true");
        int productos = contar("SELECT COUNT(*) FROM productos WHERE estado = true");
        int stockBajo = contar("""
                SELECT COUNT(*)
                FROM inventario i
                INNER JOIN productos p ON i.id_producto = p.id_producto
                WHERE i.stock_actual <= p.stock_minimo
                """);
        double ventasHoy = sumar("""
                SELECT COALESCE(SUM(total), 0)
                FROM ventas
                WHERE DATE(fecha_venta) = CURRENT_DATE
                AND estado = 'REGISTRADA'
                """);

        return new DashboardResumen(clientes, productos, stockBajo, ventasHoy);
    }

    private int contar(String sql) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            com.tecnorepara.util.LoggerUtil.error("Error en conteo dashboard", e);
        }

        return 0;
    }

    private double sumar(String sql) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            com.tecnorepara.util.LoggerUtil.error("Error en suma dashboard", e);
        }

        return 0;
    }
}