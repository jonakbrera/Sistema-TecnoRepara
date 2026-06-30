package com.tecnorepara.security;

public class PermisoUtil {

    private PermisoUtil() {
    }

    public static boolean puedeAcceder(String modulo) {
        if (!Session.haySesionActiva()) {
            return false;
        }

        if (Session.esAdministrador()) {
            return true;
        }

        if (Session.esEmpleado()) {
            return switch (modulo) {
                case "DASHBOARD",
                     "CLIENTES",
                     "PRODUCTOS",
                     "INVENTARIO",
                     "COMPRAS",
                     "VENTAS",
                     "REPORTES" -> true;
                case "USUARIOS" -> false;
                default -> false;
            };
        }

        return false;
    }
}