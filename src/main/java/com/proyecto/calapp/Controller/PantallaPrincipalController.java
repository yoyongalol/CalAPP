package com.proyecto.calapp.Controller;

import com.proyecto.calapp.DAO.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PantallaPrincipalController {
    private com.proyecto.calapp.model.Usuario usuarioActual;


    @FXML
    private Button btnPanel;

    @FXML
    private Button btnDiario;

    @FXML
    private Button btnProgreso;

    @FXML
    private Button btnMas;

    @FXML
    private Button btnAgregar;

    @FXML
    private void initialize() {
        // Aquí puedes inicializar componentes si necesitas
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
    private Label lblProgreso;

    @FXML
    public void actualizarCalorias(int caloriasConsumidas, int objetivoCalorias) {
        if (objetivoCalorias <= 0) return;
        double progreso = (double) caloriasConsumidas / objetivoCalorias;
        progreso = Math.min(progreso, 1.0);

        barraCalorias.setProgress(progreso);
        lblProgreso.setText((int)(progreso * 100) + "%");
    }

    @FXML
    private void abrirPantallaAgregar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.proyecto.calapp/pantallaSeleccionAlimentos.fxml"));
            Parent root = loader.load();

            // PASA EL CONTROLADOR PRINCIPAL
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

    @FXML
    private Label lblCaloriasRestantes;

    public void actualizarCaloriasRestantes(int restantes) {
        lblCaloriasRestantes.setText(String.valueOf(restantes));
    }
    public void setUsuarioActual(com.proyecto.calapp.model.Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public void cargarCalorias () throws SQLException {
        lblCaloriasRestantes.setText(String.valueOf(UsuarioDAO.obtenerObjetivoCaloriasPorEmail(usuarioActual.getEmail())));
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
