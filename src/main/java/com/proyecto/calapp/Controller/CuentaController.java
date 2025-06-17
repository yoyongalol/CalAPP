package com.proyecto.calapp.Controller;

import com.proyecto.calapp.DAO.UsuarioDAO;
import com.proyecto.calapp.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;

public class CuentaController {

    @FXML
    private TextField campoNombre, campoEdad, campoPeso, campoAltura, campoCalorias , campoEmail ;

    private Usuario usuarioActual;

    /**
     * Inicializa los campos de texto con los datos del usuario actual.
     *
     */
    public void setUsuario(Usuario usuario) {
        this.usuarioActual= usuario;
        campoNombre.setText(usuario.getNombreUsuario());
        campoEdad.setText(String.valueOf(usuario.getEdad()));
        campoPeso.setText(String.valueOf(usuario.getPeso()));
        campoAltura.setText(String.valueOf(usuario.getAltura()));
        campoCalorias.setText(String.valueOf(usuario.getObjetivoCalorias()));
    }

    @FXML
    public void guardarCambios(ActionEvent event) {
        String emailActual = usuarioActual.getEmail();

        String nuevoEmail = campoEmail.getText().isEmpty() ? emailActual : campoEmail.getText();
        String nuevoNombre = campoNombre.getText().isEmpty() ? usuarioActual.getNombreUsuario() : campoNombre.getText();

        int edad = campoEdad.getText().isEmpty() ? usuarioActual.getEdad() : Integer.parseInt(campoEdad.getText());
        double peso = campoPeso.getText().isEmpty() ? usuarioActual.getPeso() : Double.parseDouble(campoPeso.getText());
        double altura = campoAltura.getText().isEmpty() ? usuarioActual.getAltura() : Double.parseDouble(campoAltura.getText());
        int calorias = campoCalorias.getText().isEmpty() ? usuarioActual.getObjetivoCalorias() : Integer.parseInt(campoCalorias.getText());

        // Actualizar objeto
        usuarioActual.setEmail(nuevoEmail);
        usuarioActual.setNombreUsuario(nuevoNombre);
        usuarioActual.setEdad(edad);
        usuarioActual.setPeso(peso);
        usuarioActual.setAltura(altura);
        usuarioActual.setObjetivoCalorias(calorias);

        // Guardar en BBDD
        UsuarioDAO dao = new UsuarioDAO();
        boolean actualizado = dao.actualizar(usuarioActual, emailActual);

        if (actualizado) {
            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar usuario (quizás email ya existe).");
        }
    }


    @FXML
    private void volverAPrincipal(ActionEvent event) {
        try {
            // Cerrar cuenta
            Stage actual = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
            actual.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

