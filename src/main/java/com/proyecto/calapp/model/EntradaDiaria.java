package com.proyecto.calapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntradaDiaria {
    private int id;
    private LocalDate fecha;
    private String email_usuario;
    private List<AlimentoEntrada> alimentos = new ArrayList<>();

    public EntradaDiaria() {
        this.id = id;
        this.fecha = fecha;
        this.email_usuario = email_usuario;
        this.alimentos = alimentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public List<AlimentoEntrada> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<AlimentoEntrada> alimentos) {
        this.alimentos = alimentos;
    }
}



