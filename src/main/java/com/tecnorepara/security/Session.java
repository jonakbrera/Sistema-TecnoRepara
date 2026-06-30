package com.tecnorepara.security;

public class Session {

    private static Integer idUsuario;
    private static String nombreUsuario;
    private static String rolUsuario;

    private Session() {
    }

    public static void iniciarSesion(Integer id, String nombre, String rol) {
        idUsuario = id;
        nombreUsuario = nombre;
        rolUsuario = rol;
    }

    public static void cerrarSesion() {
        idUsuario = null;
        nombreUsuario = null;
        rolUsuario = null;
    }

    public static Integer getIdUsuario() {
        return idUsuario;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static String getRolUsuario() {
        return rolUsuario;
    }

    public static boolean esAdministrador() {
        return "ADMINISTRADOR".equalsIgnoreCase(rolUsuario);
    }

    public static boolean esEmpleado() {
        return "EMPLEADO".equalsIgnoreCase(rolUsuario);
    }

    public static boolean haySesionActiva() {
        return idUsuario != null;
    }
}