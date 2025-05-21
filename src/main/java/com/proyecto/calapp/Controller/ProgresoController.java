package com.proyecto.calapp.Controller;

import com.proyecto.calapp.model.EntradaDiaria;
import com.proyecto.calapp.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;

public class ProgresoController {
    @FXML
    private void cargarLista () {
        // Obtener la lista de entradas diarias del usuario actual

        // Aquí puedes hacer lo que necesites con la lista de entradas diarias
        //for (EntradaDiaria entrada : entradasDiarias) {
            //System.out.println("Fecha: " + entrada.getFecha());
            // Puedes acceder a otros atributos de EntradaDiaria según sea necesario

    }

    @FXML private Button btnProgreso;
    public void setUsuario(Usuario usuario) {
    }
}
