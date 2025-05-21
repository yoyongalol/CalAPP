package DAO;

import model.Alimento;
import model.AlimentoEntrada;
import model.EntradaDiaria;
import baseDatos.ConnectionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentoEntradaDAO {

    // Inserta alimento y cantidad en una entrada diaria
    public void insertarAlimentoEntrada(int idEntrada, AlimentoEntrada alimentoEntrada) throws SQLException {
        String sql = "INSERT INTO alimento_entrada (id_entrada, id_alimento, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEntrada);
            stmt.setInt(2, alimentoEntrada.getAlimento().getIdAlimento());
            stmt.setInt(3, alimentoEntrada.getCantidad());
            stmt.executeUpdate();
        }
    }

    // Devuelve todos los alimentos consumidos en una entrada diaria
    public List<AlimentoEntrada> obtenerAlimentosPorEntrada(int idEntrada) throws SQLException {
        List<AlimentoEntrada> lista = new ArrayList<>();
        String sql = "SELECT a.id, a.nombre, a.calorias, a.proteinas, a.grasas, a.carbohidratos, a.categoria, ae.cantidad " +
                "FROM Alimento_Entrada ae " +
                "JOIN Alimento a ON ae.id_alimento = a.id " +
                "WHERE ae.id_entrada = ?";

        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntrada);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Alimento alimento = new Alimento();
                    alimento.setIdAlimento(rs.getInt("id"));
                    alimento.setNombreAlimento(rs.getString("nombre"));
                    alimento.setCalorias(rs.getInt("calorias"));
                    alimento.setProteinas(rs.getDouble("proteinas"));
                    alimento.setGrasas(rs.getDouble("grasas"));
                    alimento.setCarbohidratos(rs.getDouble("carbohidratos"));
                    alimento.setCategoria(rs.getString("categoria"));

                    AlimentoEntrada ae = new AlimentoEntrada();
                    ae.setAlimento(alimento);
                    ae.setCantidad(rs.getInt("cantidad"));

                    lista.add(ae);
                }
            }
        }
        return lista;
    }
}

