package com.proyecto.calapp.DAO;

import com.proyecto.calapp.model.Alimento;
import com.proyecto.calapp.model.AlimentoUsuario;
import com.proyecto.calapp.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.proyecto.calapp.baseDatos.ConnectionBD.getConnection;

public class AlimentoUsuarioDAO {

    private final static String SQL_INSERT = "INSERT INTO alimentousuario (id_alimento, email, fecha, cantidad) VALUES (?, ?, ?, ?)";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM alimentousuario WHERE id_alimento = ? AND email = ? AND fecha = ?";
    private final static String SQL_SELECT_ALL = "SELECT * FROM alimentousuario";
    private final static String SQL_UPDATE = "UPDATE alimentousuario SET cantidad = ? WHERE id_alimento = ? AND email = ? AND fecha = ?";
    private final static String SQL_DELETE = "DELETE FROM alimentousuario WHERE id_alimento = ? AND email = ? AND fecha = ?";

    private final Connection connection;

    public AlimentoUsuarioDAO(Connection connection) {

        this.connection = connection;
    }

    public static void registrarConsumo(AlimentoUsuario consumo) {
        String sql = "INSERT INTO alimentosusuario (usuario_email, alimento_id, fecha, cantidad) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consumo.getUsuario().getEmail());
            stmt.setInt(2, consumo.getAlimento().getId());
            stmt.setDate(3, new java.sql.Date(consumo.getFecha().getTime()));
            stmt.setInt(4, consumo.getCantidad());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AlimentoUsuario> obtenerConsumosPorUsuario(String email) {
        String sql = "SELECT * FROM alimentosusuario WHERE usuario_email = ?";
        List<AlimentoUsuario> consumos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(email);

                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("alimento_id"));
                // Opcional: buscar más info del alimento con otro DAO

                Date fecha = rs.getDate("fecha");
                int cantidad = rs.getInt("cantidad");

                AlimentoUsuario consumo = new AlimentoUsuario(usuario, alimento, fecha, cantidad);
                consumos.add(consumo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consumos;
    }

    public void eliminarConsumo(String email, int alimentoId, Date fecha) {
        String sql = "DELETE FROM alimentosusuario WHERE usuario_email = ? AND alimento_id = ? AND fecha = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setInt(2, alimentoId);
            stmt.setDate(3, new java.sql.Date(fecha.getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarConsumo(AlimentoUsuario consumo) {
        String sql = "UPDATE alimentosusuario SET cantidad = ? " +
                "WHERE usuario_email = ? AND alimento_id = ? AND fecha = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consumo.getCantidad());
            stmt.setString(2, consumo.getUsuario().getEmail());
            stmt.setInt(3, consumo.getAlimento().getId());
            stmt.setDate(4, new java.sql.Date(consumo.getFecha().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AlimentoUsuario> obtenerPorFecha(String email, Date fecha) {
        String sql = "SELECT * FROM alimentosusuario WHERE usuario_email = ? AND fecha = ?";
        List<AlimentoUsuario> consumos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setDate(2, new java.sql.Date(fecha.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(email);

                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt("alimento_id"));
                // Aquí podrías usar AlimentoDAO para obtener más datos del alimento

                int cantidad = rs.getInt("cantidad");

                AlimentoUsuario consumo = new AlimentoUsuario(usuario, alimento, fecha, cantidad);
                consumos.add(consumo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consumos;
    }

    public static int obtenerCaloriasConsumidasHoy(String email) {
        String sql = """
        SELECT SUM(a.calorias * au.cantidad) AS total_calorias
        FROM alimentosusuario au
        JOIN alimento a ON au.alimento_id = a.id
        WHERE au.usuario_email = ? AND au.fecha = ?
    """;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total_calorias");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}

