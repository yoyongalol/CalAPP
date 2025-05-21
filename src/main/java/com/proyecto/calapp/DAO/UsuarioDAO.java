package com.proyecto.calapp.DAO;
import com.proyecto.calapp.baseDatos.ConnectionBD;
import com.proyecto.calapp.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final static String SQL_ALL = "SELECT * FROM usuario";
    private final static String SQL_INSERT = "INSERT INTO usuario (nombre, edad, peso, altura, objetivo_calorias) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE usuario SET nombre = ?, edad = ?, peso = ?, altura = ?, objetivo_calorias = ?";

    public static List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Connection con = ConnectionBD.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombreUsuario(rs.getString("email"));
                usuario.setNombreUsuario(rs.getString("contraseña"));
                usuario.setNombreUsuario(rs.getString("nombre"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setPeso(rs.getDouble("peso"));
                usuario.setAltura(rs.getDouble("altura"));
                usuario.setObjetivoCalorias(rs.getInt("objetivo_calorias"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario buscarPorEmailYContraseña(String email, String contraseña) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND contraseña = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, contraseña);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setNombreUsuario(rs.getString("nombre"));
                    u.setEdad(rs.getInt("edad"));
                    u.setPeso(rs.getDouble("peso"));
                    u.setAltura(rs.getDouble("altura"));
                    u.setObjetivoCalorias(rs.getInt("objetivo_calorias"));
                    u.setEmail(rs.getString("email"));
                    // Puedes agregar más campos si necesitas
                    return u;
                }
            }
        }
        return null;
    }


    /*public void insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario(email, contraseña, nombre, edad, peso, altura, objetivo_calorias) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getContraseña());
            stmt.setString(3, usuario.getNombreUsuario());
            stmt.setInt(4, usuario.getEdad());
            stmt.setDouble(5, usuario.getPeso());
            stmt.setDouble(6, usuario.getAltura());
            stmt.setInt(7, usuario.getObjetivoCalorias());

            stmt.executeUpdate();
        }
    }*/

    public boolean insertar(Usuario usuario) throws SQLException {
        String checkSql = "SELECT email FROM usuario WHERE email = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, usuario.getEmail());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                // Ya existe un usuario con ese email
                return false;
            }
        }

        // Si no existe, insertar
        String sql = "INSERT INTO usuario(email, contraseña, nombre, edad, peso, altura, objetivo_calorias) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getContraseña());
            stmt.setString(3, usuario.getNombreUsuario());
            stmt.setInt(4, usuario.getEdad());
            stmt.setDouble(5, usuario.getPeso());
            stmt.setDouble(6, usuario.getAltura());
            stmt.setInt(7, usuario.getObjetivoCalorias());

            stmt.executeUpdate();
            return true;
        }
    }


    public boolean actualizar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionBD.getConnection();

            // Si el usuario quiere cambiar el email
            if (!usuario.getEmail().equals(usuario.getEmailAnterior())) {
                String checkSql = "SELECT email FROM usuario WHERE email = ?";
                checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, usuario.getEmail());
                rs = checkStmt.executeQuery();

                if (rs.next()) {
                    // El nuevo email ya existe, no se puede actualizar
                    return false;
                }
            }

            // Ahora se realiza el UPDATE
            String updateSql = "UPDATE usuario SET email = ?, nombre = ?, edad = ?, peso = ?, altura = ?, objetivo_calorias = ? WHERE email = ?";
            updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, usuario.getEmail());
            updateStmt.setString(2, usuario.getNombreUsuario());
            updateStmt.setInt(3, usuario.getEdad());
            updateStmt.setDouble(4, usuario.getPeso());
            updateStmt.setDouble(5, usuario.getAltura());
            updateStmt.setInt(6, usuario.getObjetivoCalorias());
            updateStmt.setString(7, usuario.getEmailAnterior());

            int filas = updateStmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
            } catch (Exception ignored) {
            }
            try {
                if (checkStmt != null) checkStmt.close();
            } catch (Exception ignored) {
            }
            try {
                if (updateStmt != null) updateStmt.close();
            } catch (Exception ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {
            }
        }
    }

    public static int obtenerObjetivoCaloriasPorEmail(String email) throws SQLException {
        String sql = "SELECT objetivo_calorias FROM usuario WHERE email = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("objetivo_calorias");
            }
            return 2000;
        }
    }
}
