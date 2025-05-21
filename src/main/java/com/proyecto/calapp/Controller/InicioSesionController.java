package com.proyecto.calapp.Controller;

import com.proyecto.calapp.DAO.UsuarioDAO;
import com.proyecto.calapp.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class InicioSesionController {

    @FXML
    private TextField campoEmail;
    @FXML
    private TextField campoContraseña;

    @FXML
    private void IniciarSesion() throws SQLException {
        String email = campoEmail.getText();
        String contraseña = campoContraseña.getText();

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscarPorEmailYContraseña(email, contraseña);
        UsuarioActualController.getInstancia().setUsuarioActual(usuario);

        if (usuario != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.proyecto.calapp/pantallaPrincipal.fxml"));
                Parent root = loader.load();

                // Obtener el controlador y pasarle el usuario logueado
                PantallaPrincipalController controller = loader.getController();
                controller.setUsuarioActual(usuario);

                Stage stage = (Stage) campoEmail.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    @FXML
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    private void irARegistro (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.proyecto.calapp/registrarse.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registro");
            stage.show();

            // Cerrar login
            Stage actual = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
            actual.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
