package com.itlm.fernandoalberto.firedragonalerthackaton;

public class Usuarios {
    private String Nombre;
    private String Correo;

    public Usuarios() {
    }

    public Usuarios(String nombre, String correo) {
        Nombre = nombre;
        Correo = correo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
