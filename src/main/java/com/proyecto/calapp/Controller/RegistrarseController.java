package com.proyecto.calapp.Controller;

import com.proyecto.calapp.DAO.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.proyecto.calapp.model.Usuario;

public class RegistrarseController {

    @FXML
    private TextField campoCorreo;
    @FXML
    private TextField campoContraseña;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoEdad;
    @FXML
    private TextField campoPeso;
    @FXML
    private TextField campoAltura;

    @FXML
    private void registrarUsuario(ActionEvent event) {
        try {
            // 1. Obtener datos del formulario

            String nombre = campoNombre.getText();
            int edad = Integer.parseInt(campoEdad.getText());
            double peso = Double.parseDouble(campoPeso.getText());
            double altura = Double.parseDouble(campoAltura.getText());
            String email = campoCorreo.getText();
            String contraseña = campoContraseña.getText();


            // 2. Crear el objeto Usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setContraseña(contraseña);
            nuevoUsuario.setNombreUsuario(nombre);
            nuevoUsuario.setEdad(edad);
            nuevoUsuario.setPeso(peso);
            nuevoUsuario.setAltura(altura);


            // 3. Guardar en la base de datos
            UsuarioDAO dao = new UsuarioDAO();
            dao.insertar(nuevoUsuario);

            // 4. Ir a la pantalla de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.proyecto.calapp/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Iniciar sesión");
            stage.show();

            // 5. Cerrar la ventana actual
            Stage actualStage = (Stage) campoNombre.getScene().getWindow();
            actualStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            // Aquí podrías mostrar un mensaje de error al usuario si lo deseas
        }
    }
    @FXML
    private void irALogin (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.proyecto.calapp/login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Iniciar sesión");
            stage.show();

            // Cerrar registro
            Stage actual = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
            actual.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
