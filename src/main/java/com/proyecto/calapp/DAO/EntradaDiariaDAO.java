package DAO;

import model.EntradaDiaria;
import model.Usuario;
import model.AlimentoEntrada;
import baseDatos.ConnectionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntradaDiariaDAO {

    private static final String SQL_INSERT = "INSERT INTO entradadiaria(fecha, id) VALUES (?, ?)";

    // Inserta una nueva entrada diaria para un usuario.
    // Nota: Aquí se inserta solo la "cabecera" (fecha e id_usuario)
    // Luego, deberás insertar los registros en la tabla puente (AlimentoEntrada) en otro DAO o método.
    public void insertarEntrada(EntradaDiaria entrada) throws SQLException {
        Connection conn = ConnectionBD.getConnection();
        try {
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setDate(1, Date.valueOf(entrada.getFecha()));
            stmt.setInt(2, entrada.getUsuario().getIdUsuario());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("");
            }

        }catch (SQLException e){
         throw new SQLException();
        }
    }


    // Obtener todas las entradas para un usuario
    public List<EntradaDiaria> getEntradasPorUsuario(Usuario usuario) throws SQLException {
        List<EntradaDiaria> entradas = new ArrayList<>();
        String sql = "SELECT * FROM EntradaDiaria WHERE id = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getIdUsuario());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EntradaDiaria entrada = new EntradaDiaria();
                    entrada.setId(rs.getInt("id"));
                    entrada.setFecha(rs.getDate("fecha").toLocalDate());
                    entrada.setUsuario(usuario);
                    // La lista de alimentos se debe completar con otro método, si lo requieres.
                    entradas.add(entrada);
                }
            }
        }
        return entradas;
    }

    // Podrías agregar métodos para actualizar y eliminar entradas, si es necesario.
}

