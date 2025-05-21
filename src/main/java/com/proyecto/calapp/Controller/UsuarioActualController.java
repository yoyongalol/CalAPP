package com.proyecto.calapp.Controller;

import com.proyecto.calapp.model.Usuario;

public class UsuarioActualController {

    private static UsuarioActualController instancia;
    private Usuario usuarioActual;
    private String credenciales; // Puedes cambiar el tipo según tus necesidades

    private UsuarioActualController() {
        // Constructor privado
    }

    public static UsuarioActualController getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioActualController();
        }
        return instancia;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public String getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(String credenciales) {
        this.credenciales = credenciales;
    }
}
