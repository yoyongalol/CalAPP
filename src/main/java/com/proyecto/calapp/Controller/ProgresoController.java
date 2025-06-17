package com.proyecto.calapp.Controller;

import com.proyecto.calapp.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class ProgresoController {
    private Usuario usuario;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Label lblPromedio, lblMaximo, lblTotal;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        cargarDatosDelUsuario();
    }

    public void initialize() {
        if (usuario != null) {
            cargarDatosDelUsuario();
        }
    }

    private void cargarDatosDelUsuario() {
        if (usuario == null) return;

        String nombreUsuario = usuario.getNombreUsuario();
        int calorias = usuario.getObjetivoCalorias();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(nombreUsuario);
        series.getData().add(new XYChart.Data<>(nombreUsuario, calorias));

        barChart.getData().clear();
        barChart.getData().add(series);

        lblTotal.setText(String.valueOf(calorias));
        lblMaximo.setText(String.valueOf(calorias));
        lblPromedio.setText(String.valueOf(calorias));
    }
}


