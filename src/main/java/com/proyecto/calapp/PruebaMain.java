import DAO.AlimentoDAO;
import DAO.AlimentoEntradaDAO;
import DAO.EntradaDiariaDAO;
import DAO.UsuarioDAO;
import model.Alimento;
import model.AlimentoEntrada;
import model.EntradaDiaria;
import model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PruebaMain {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/calapp";
        String user = "root";
        String password = "";

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            AlimentoDAO alimentoDAO = new AlimentoDAO();
            EntradaDiariaDAO entradaDiariaDAO = new EntradaDiariaDAO();
            AlimentoEntradaDAO alimentoEntradaDAO = new AlimentoEntradaDAO();

            // Obtener usuario existente
            Usuario usuario = usuarioDAO.findAll().get(0); // o busca por ID

            // Crear nueva entrada diaria
            EntradaDiaria entrada = new EntradaDiaria();
            entrada.setUsuario(usuario);
            entrada.setFecha(LocalDate.now());
            entradaDiariaDAO.insertarEntrada(entrada);

            // Obtener algunos alimentos
            List<Alimento> alimentos = alimentoDAO.getTodos();

            // Asociar alimento con cantidad
            AlimentoEntrada arroz = new AlimentoEntrada(alimentos.get(0), 150); // 150g de arroz
            alimentoEntradaDAO.insertarAlimentoEntrada(entrada.getId(), arroz);

            AlimentoEntrada pollo = new AlimentoEntrada(alimentos.get(1), 200); // 200g de pollo
            alimentoEntradaDAO.insertarAlimentoEntrada(entrada.getId(), pollo);

            // Mostrar lo registrado
            List<AlimentoEntrada> consumidos = alimentoEntradaDAO.obtenerAlimentosPorEntrada(entrada.getId());
            System.out.println("🍽️ Alimentos registrados hoy:");
            for (AlimentoEntrada ae : consumidos) {
                System.out.println("- " + ae.getAlimento().getNombreAlimento() + " (" + ae.getCantidad() + "g): " + ae.getCaloriasTotales() + " kcal");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
