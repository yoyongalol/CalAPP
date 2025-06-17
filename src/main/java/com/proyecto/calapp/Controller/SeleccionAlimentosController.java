package com.proyecto.calapp.Controller;
import com.proyecto.calapp.DAO.AlimentoDAO;
import com.proyecto.calapp.DAO.AlimentoUsuarioDAO;
import com.proyecto.calapp.DAO.UsuarioDAO;
import com.proyecto.calapp.baseDatos.ConnectionBD;
import com.proyecto.calapp.model.Alimento;
import com.proyecto.calapp.model.AlimentoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.proyecto.calapp.baseDatos.ConnectionBD.getConnection;

public class SeleccionAlimentosController {
    @FXML private AlimentoDAO alimentoDAO = new AlimentoDAO();
    @FXML private UsuarioDAO usuarioDAO = new UsuarioDAO();
    @FXML private ProgressBar barraCalorias;
    @FXML private Label lblProgreso;
    @FXML private String emailUsuario;
    @FXML private TextField campoBuscar;
    @FXML private ListView<String> listaAlimentos;
    @FXML private TextField campoNombre;
    @FXML private TextField campoCalorias;
    @FXML private TextField campoProteinas;
    @FXML private TextField campoGrasas;
    @FXML private TextField campoCarbohidratos;
    @FXML private TextField campoCategoria;

    private List<Alimento> alimentosTotales;
    private PantallaPrincipalController pantallaPrincipalController;

    public void setPantallaPrincipalController(PantallaPrincipalController controller) {
        this.pantallaPrincipalController = controller;
    }

    public SeleccionAlimentosController(String emailUsuario, ProgressBar barraCalorias, Label lblProgreso) {
        this.emailUsuario = emailUsuario;
        this.barraCalorias = barraCalorias;
        this.lblProgreso = lblProgreso;
    }
    public SeleccionAlimentosController() {
        // Constructor vacío para uso de FXMLLoader
    }

    @FXML
    public void initialize() throws SQLException {
        alimentosTotales = AlimentoDAO.getTodos();
        cargarLista(alimentosTotales);
    }

    @FXML
    public void buscarAlimento() {
        String texto = campoBuscar.getText().toLowerCase().trim();
        List<Alimento> filtrados = alimentosTotales.stream()
                .filter(a -> a.getNombre().toLowerCase().contains(texto))
                .collect(Collectors.toList());
        cargarLista(filtrados);
    }

    private List<Alimento> alimentosVisibles = new ArrayList<>();

    private void cargarLista(List<Alimento> alimentos) {
        listaAlimentos.getItems().clear();
        alimentosVisibles.clear();

        for (Alimento alimento : alimentos) {
            listaAlimentos.getItems().add(alimento.getNombre());
            alimentosVisibles.add(alimento);
        }
    }

    @FXML
    private void añadirAlimento() {
        int index = listaAlimentos.getSelectionModel().getSelectedIndex();
        if (index < 0) return;

        Alimento alimentoSeleccionado = alimentosVisibles.get(index);

        // Crear el objeto AlimentoUsuario
        AlimentoUsuario nuevo = new AlimentoUsuario(
                UsuarioActualController.getInstancia().getUsuarioActual(),
                alimentoSeleccionado,
                new Date(),
                1
        );

        // Registrar el alimento consumido
        AlimentoUsuarioDAO dao = new AlimentoUsuarioDAO(getConnection());
        dao.registrarConsumo(nuevo);

        // Obtener el total de calorías consumidas hoy por el usuario
        int caloriasHoy = dao.obtenerCaloriasConsumidasHoy(
                UsuarioActualController.getInstancia().getUsuarioActual().getEmail()
        );

        // Obtener el objetivo diario del usuario
        int objetivo = UsuarioActualController.getInstancia().getUsuarioActual().getObjetivoCalorias();

        // Calcular las calorías restantes
        int caloriasRestantes = objetivo - caloriasHoy;

        // Actualizar el label en la pantalla principal
        if (pantallaPrincipalController != null) {
            pantallaPrincipalController.actualizarLabelCalorias(caloriasRestantes);
        } else {
            System.err.println("pantallaPrincipalController es null");
        }
    }




    @FXML
    private void registrarAlimento(ActionEvent event) {
        try {
            String nombre = campoNombre.getText();
            int calorias = Integer.parseInt(campoCalorias.getText());
            double proteinas = Double.parseDouble(campoProteinas.getText());
            double grasas = Double.parseDouble(campoGrasas.getText());
            double carbohidratos = Double.parseDouble(campoCarbohidratos.getText());
            String categoria = campoCategoria.getText();

            Alimento nuevoAlimento = new Alimento();
            nuevoAlimento.setNombre(nombre);
            nuevoAlimento.setCalorias(calorias);
            nuevoAlimento.setGrasas(grasas);
            nuevoAlimento.setProteinas(proteinas);
            nuevoAlimento.setCarbohidratos(carbohidratos);
            nuevoAlimento.setCategoria(categoria);

            alimentoDAO.insertar(nuevoAlimento);

            // Actualiza la lista
            alimentosTotales = AlimentoDAO.getTodos();
            cargarLista(alimentosTotales);

            // Opcional: limpia los campos
            campoNombre.clear();
            campoCalorias.clear();
            campoProteinas.clear();
            campoGrasas.clear();
            campoCarbohidratos.clear();
            campoCategoria.clear();

            System.out.println("Alimento registrado correctamente.");

        } catch (NumberFormatException e) {
            System.out.println("Error: formato numérico incorrecto.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el alimento: " + e.getMessage());
        }
    }


    @FXML
    public void eliminarAlimento () {
        List<Integer> indices = listaAlimentos.getSelectionModel().getSelectedIndices();

        if (indices.isEmpty()) {
            System.out.println("Error: No hay alimentos seleccionados para eliminar.");
            return;
        }

        for (Integer index : indices) {
            Alimento alimento = alimentosVisibles.get(index);
            try {
                alimentoDAO.eliminarPorId(alimento.getId());
                alimentosTotales.remove(alimento); // también lo eliminas de la lista global
            } catch (SQLException e) {
                System.out.println("Error: No se pudo eliminar el alimento: " + alimento.getNombre());
            }
        }

        // Recargar la lista para reflejar cambios
        cargarLista(alimentosTotales);
    }

}




