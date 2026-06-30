package com.tecnorepara.model;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String usuario;
    private String rol;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String usuario, String rol, boolean estado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.rol = rol;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getRol() {
        return rol;
    }

    public boolean isEstado() {
        return estado;
    }
}