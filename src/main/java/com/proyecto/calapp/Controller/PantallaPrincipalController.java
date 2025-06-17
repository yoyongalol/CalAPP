package com.proyecto.calapp.Controller;

import com.proyecto.calapp.DAO.AlimentoUsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaPrincipalController {
    private com.proyecto.calapp.model.Usuario usuarioActual;

    @FXML private Label lblCaloriasRestantes;
    @FXML private Label lblAlimentos;
    @FXML private Label lblObjetivo;
    @FXML private Label lblEjercicio;

    @FXML
    private Button btnPanel;

    @FXML
    private Button btnDiario;

    @FXML
    private Button btnProgreso;

    @FXML
    private Button btnMas;

    @FXML
    private Label lblCalorias;

    @FXML
    private Button btnAgregar;

    @FXML
    private void initialize() {
        lblCalorias.setText(String.valueOf(UsuarioActualController.getInstancia().getUsuarioActual().getObjetivoCalorias()));
        if (usuarioActual != null) {
            lblCalorias.setText(String.valueOf(usuarioActual.getObjetivoCalorias()));
            int caloriasHoy = AlimentoUsuarioDAO.obtenerCaloriasConsumidasHoy(usuarioActual.getEmail());
            actualizarLabelCalorias(usuarioActual.getObjetivoCalorias() - caloriasHoy);
        } else {
            System.err.println("usuarioActual no fue seteado antes de initialize()");
        }
    }


    @FXML
    private void manejarPanel() {
        System.out.println("Ir a Panel");
    }

    @FXML
    private void manejarDiario() {
        System.out.println("Actividad Diaria");;
    }

    @FXML
    private void manejarProgreso() {
        System.out.println("Progreso general");
    }

    @FXML
    private void manejarCuenta() {
        System.out.println("Modificar cuenta");
    }

    @FXML
    private void manejarAgregar() {
        System.out.println("Agregar alimentos");
    }
    @FXML
    private ProgressBar barraCalorias;

    @FXML
    private Label getLblCaloriasRestantes;
    @FXML
    private Label labelCalorias;

    public void actualizarLabelCalorias(int caloriasRestantes) {
        labelCalorias.setText("Calorías restantes hoy: " + caloriasRestantes);
    }


    @FXML
    private void abrirPantallaAgregar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.proyecto.calapp/pantallaSeleccionAlimentos.fxml"));
            Parent root = loader.load();

            SeleccionAlimentosController controller = loader.getController();
            controller.setPantallaPrincipalController(this);

            Stage stage = new Stage();
            stage.setTitle("Seleccionar Alimentos");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void abrirPantallaCuenta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.proyecto.calapp/cuenta.fxml"));
            Parent root = loader.load();

            CuentaController controller = loader.getController();
            controller.setUsuario(usuarioActual);

            Stage stage = new Stage();
            stage.setTitle("Cuenta");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void actualizarCaloriasRestantes(int restantes) {
        lblCaloriasRestantes.setText(String.valueOf(restantes));
    }
    public void setUsuarioActual(com.proyecto.calapp.model.Usuario usuario) {
        this.usuarioActual = usuario;
    }

    @FXML
    private void abrirPantallaProgreso() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.proyecto.calapp/progreso.fxml"));
            Parent root = loader.load();

            ProgresoController controller = loader.getController();
            controller.setUsuario(usuarioActual);

            Stage stage = new Stage();
            stage.setTitle("Progreso");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
