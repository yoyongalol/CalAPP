package com.proyecto.calapp.DAO;

import com.proyecto.calapp.model.EntradaDiaria;
import com.proyecto.calapp.model.Usuario;
import com.proyecto.calapp.baseDatos.ConnectionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntradaDiariaDAO {

    private static final String SQL_INSERT = "INSERT INTO entradadiaria(fecha, ) VALUES (?, ?)";

    public static List<EntradaDiaria> getTodos() throws SQLException {
        List<EntradaDiaria> entradaDiarias = new ArrayList<>();
        String sql = "SELECT * FROM entradadiaria";
        try (Connection conn = ConnectionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EntradaDiaria registro = new EntradaDiaria();
                registro.setEmail_usuario(rs.getString("email_usuario"));
                registro.setFecha(rs.getDate("fecha").toLocalDate());
                registro.setId(rs.getInt("id"));
                registro.setAlimentos(AlimentoEntradaDAO.obtenerAlimentosPorEntrada(registro.getId()));
            }
        }
        return entradaDiarias;
    }

    // Inserta una nueva entrada diaria para un usuario.
    // Nota: Aquí se inserta solo la "cabecera" (fecha e id_usuario)
    // Luego, deberás insertar los registros en la tabla puente (AlimentoEntrada) en otro DAO o método.
    public int insertarEntrada(String email, LocalDate fecha) throws SQLException {
        String sql = "INSERT INTO entrada_diaria (email_usuario, fecha) VALUES (?, ?)";

        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, email);
            stmt.setDate(2, Date.valueOf(fecha));

            int filas = stmt.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo insertar la entrada diaria.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Devuelve el id generado
                } else {
                    throw new SQLException("No se pudo obtener el ID de la entrada insertada.");
                }
            }
        }
    }



    // Obtener todas las entradas para un usuario
    public int obtenerIdEntradaDiaria(String email, LocalDate fecha) throws SQLException {
        String sql = "SELECT id FROM entrada_diaria WHERE email_usuario = ? AND fecha = ?";

        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setDate(2, Date.valueOf(fecha));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1; // No existe
                }
            }
        }
    }

    public int obtenerCaloriasTotalesPorEmailYFecha(String email, LocalDate fecha) throws SQLException {
        // Haz una suma de las calorías consumidas ese día por ese usuario
        String sql = "SELECT SUM(calorias) FROM alimentos_consumidos WHERE email_usuario = ? AND fecha = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setDate(2, Date.valueOf(fecha));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

}

