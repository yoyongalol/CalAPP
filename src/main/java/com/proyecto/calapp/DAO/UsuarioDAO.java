package DAO;
import baseDatos.ConnectionBD;
import model.Usuario;
import model.Alimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final static String SQL_ALL = "SELECT * FROM usuario";
    private final static String SQL_INSERT = "INSERT INTO usuario (nombre, edad, peso, altura, objetivo_calorias) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE usuario SET nombre = ?, edad = ?, peso = ?, altura = ?, objetivo_calorias = ? WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM usuario WHERE id = ?";


    public static List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Connection con = ConnectionBD.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id"));
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
}
