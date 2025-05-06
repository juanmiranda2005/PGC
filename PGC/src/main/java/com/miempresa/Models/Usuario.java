package com.miempresa.Models;

public class Usuario {

    public enum TipoUsuario {
        CLIENTE_NATURAL,
        CONTRATISTA,
        CLIENTE_EMPRESA,
        TRABAJADOR,
        EMPRESA_ADMINISTRADORA
    }

    private String username;
    private String password;
    private TipoUsuario tipoUsuario;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private int identificacion;

    public Usuario(String username, String password, TipoUsuario tipoUsuario,
                   String nombre, String apellido, String correoElectronico, int identificacion) {
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.identificacion = identificacion;
    }

    // Getters
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsername() {
        return username;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Nombre: " + nombre + " " + apellido + ", Tipo: " + tipoUsuario;
    }
}
