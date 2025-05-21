package com.proyecto.calapp.model;

public class AlimentoEntrada {
    private Alimento alimento;
    private int cantidad; // Por ejemplo, en gramos o unidades

    public AlimentoEntrada() {
    }

    public AlimentoEntrada(Alimento alimento, int cantidad) {
        this.alimento = alimento;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método opcional para obtener las calorías totales de este alimento en la entrada
    public int getCaloriasTotales() {
        // Suponiendo que la cantidad está en gramos y que la información en Alimento se basa en 100 gramos.
        return (alimento.getCalorias() * cantidad) / 100;
    }
}

