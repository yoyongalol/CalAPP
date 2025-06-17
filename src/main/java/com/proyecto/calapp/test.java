package com.proyecto.calapp;

import com.proyecto.calapp.DAO.UsuarioDAO;
import com.proyecto.calapp.model.Usuario;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        Usuario usuario = new Usuario("hoal@gamilc.om", "1234", "hoal", 20, 70, 1.75, 2000);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insertar(usuario);
        Usuario usuarioNuevo = new Usuario ("Pepe", "5545", "Pepe", 20, 70, 1.75, 2000);
        usuario.setEmail("Peppe");
        System.out.println(usuarioDAO.actualizar(usuarioNuevo, usuario.getEmail()));

    }
}
