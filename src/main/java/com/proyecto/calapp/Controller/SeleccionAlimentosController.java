package com.proyecto.calapp.Controller;

import com.proyecto.calapp.DAO.AlimentoDAO;
import com.proyecto.calapp.DAO.AlimentoEntradaDAO;
import com.proyecto.calapp.DAO.EntradaDiariaDAO;
import com.proyecto.calapp.DAO.UsuarioDAO;
import com.proyecto.calapp.model.Alimento;
import com.proyecto.calapp.model.AlimentoEntrada;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SeleccionAlimentosController {
    @FXML private AlimentoDAO alimentoDAO = new AlimentoDAO();
    @FXML private UsuarioDAO usuarioDAO = new UsuarioDAO();
    @FXML private EntradaDiariaDAO entradaDiariaDAO = new EntradaDiariaDAO();
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
                .filter(a -> a.getNombreAlimento().toLowerCase().contains(texto))
                .collect(Collectors.toList());
        cargarLista(filtrados);
    }

    @FXML
    private void cargarLista(List<Alimento> lista) {
        listaAlimentos.getItems().clear();
        for (Alimento a : lista) {
            listaAlimentos.getItems().add(a.getNombreAlimento() + " (" + a.getCalorias() + " cal)");
        }
    }

    @FXML
    public void registrarAlimento() {
        try {
            String nombre = campoNombre.getText();
            int calorias = Integer.parseInt(campoCalorias.getText());
            double proteinas = Double.parseDouble(campoProteinas.getText());
            double grasas = Double.parseDouble(campoGrasas.getText());
            double carbohidratos = Double.parseDouble(campoCarbohidratos.getText());
            String categoria = campoCategoria.getText();

            Alimento nuevo = new Alimento(nombre, calorias, proteinas, grasas, carbohidratos, categoria);
            AlimentoDAO.insertar(nuevo);

            // Refrescar lista
            alimentosTotales = AlimentoDAO.getTodos();
            cargarLista(alimentosTotales);

            // Limpiar campos
            campoNombre.clear();
            campoCalorias.clear();
            campoProteinas.clear();
            campoGrasas.clear();
            campoCarbohidratos.clear();
            campoCategoria.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void agregarSeleccionados() {
        List<String> seleccionados = listaAlimentos.getSelectionModel().getSelectedItems();
        System.out.println("Seleccionados: " + seleccionados);
    }

    @FXML
    public void agregarAlimentos (Alimento alimento, int cantidad) throws SQLException {

        int idEntrada = entradaDiariaDAO.obtenerIdEntradaDiaria(emailUsuario, LocalDate.now());
        if (idEntrada == -1) {
            idEntrada = entradaDiariaDAO.insertarEntrada(emailUsuario, LocalDate.now());
        }

        // 2. Crear objeto AlimentoEntrada
        AlimentoEntrada entrada = new AlimentoEntrada();
        entrada.setAlimento(alimento);
        entrada.setCantidad(cantidad);

        // 3. Insertar alimento en esa entrada
        AlimentoEntradaDAO.insertarAlimentoEntrada(idEntrada, entrada);

        // 4. Calcular calorías totales consumidas en el día
        List<AlimentoEntrada> alimentosDelDia = AlimentoEntradaDAO.obtenerAlimentosPorEntrada(idEntrada);
        int caloriasConsumidas = 0;
        for (AlimentoEntrada ae : alimentosDelDia) {
            caloriasConsumidas += ae.getAlimento().getCalorias() * ae.getCantidad();
        }

        // 5. Obtener objetivo calorías
        int objetivo = usuarioDAO.obtenerObjetivoCaloriasPorEmail(emailUsuario);

        // 6. Actualizar barra
        if (pantallaPrincipalController != null) {
            pantallaPrincipalController.actualizarCalorias(caloriasConsumidas, objetivo);
        }

    }

    @FXML
    public void actualizarBarraCalorias(int consumidas, int objetivo) {
        if (objetivo <= 0) return;
        double progreso = (double) consumidas / objetivo;
        progreso = Math.min(progreso, 1.0);

        barraCalorias.setProgress(progreso);
        lblProgreso.setText((int)(progreso * 100) + "%");
    }
}

