package com.proyecto.calapp.DAO;

import com.proyecto.calapp.model.Alimento;
import com.proyecto.calapp.baseDatos.ConnectionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO {

    // Inserta un nuevo alimento
    public static void insertar(Alimento alimento) throws SQLException {
        String sql = "INSERT INTO Alimento (nombre, calorias, proteinas, grasas, carbohidratos, categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, alimento.getNombreAlimento());
            stmt.setInt(2, alimento.getCalorias());
            stmt.setDouble(3, alimento.getProteinas());
            stmt.setDouble(4, alimento.getGrasas());
            stmt.setDouble(5, alimento.getCarbohidratos());
            stmt.setString(6, alimento.getCategoria());
            stmt.executeUpdate();
        }
    }

    // Devuelve todos los alimentos de la base de datos
    public static List<Alimento> getTodos() throws SQLException {
        List<Alimento> alimentos = new ArrayList<>();
        String sql = "SELECT * FROM alimento";
        try (Connection conn = ConnectionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setIdAlimento(rs.getInt("id"));
                alimento.setNombreAlimento(rs.getString("nombre"));
                alimento.setCalorias(rs.getInt("calorias"));
                alimento.setProteinas(rs.getDouble("proteinas"));
                alimento.setGrasas(rs.getDouble("grasas"));
                alimento.setCarbohidratos(rs.getDouble("carbohidratos"));
                alimento.setCategoria(rs.getString("categoria"));
                alimentos.add(alimento);
            }
        }
        return alimentos;
    }

    // Buscar alimento por ID
    public Alimento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM alimento WHERE id = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Alimento alimento = new Alimento();
                    alimento.setIdAlimento(rs.getInt("id"));
                    alimento.setNombreAlimento(rs.getString("nombre"));
                    alimento.setCalorias(rs.getInt("calorias"));
                    alimento.setProteinas(rs.getDouble("proteinas"));
                    alimento.setGrasas(rs.getDouble("grasas"));
                    alimento.setCarbohidratos(rs.getDouble("carbohidratos"));
                    alimento.setCategoria(rs.getString("categoria"));
                    return alimento;
                }
            }
        }
        return null;
    }

    // Eliminar alimento por ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Alimento WHERE id = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Actualizar un alimento existente
    public void actualizar(Alimento alimento) throws SQLException {
        String sql = "UPDATE Alimento SET nombre = ?, calorias = ?, proteinas = ?, grasas = ?, carbohidratos = ?, categoria = ? WHERE id = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, alimento.getNombreAlimento());
            stmt.setInt(2, alimento.getCalorias());
            stmt.setDouble(3, alimento.getProteinas());
            stmt.setDouble(4, alimento.getGrasas());
            stmt.setDouble(5, alimento.getCarbohidratos());
            stmt.setString(6, alimento.getCategoria());
            stmt.setInt(7, alimento.getIdAlimento());
            stmt.executeUpdate();
        }
    }

}
