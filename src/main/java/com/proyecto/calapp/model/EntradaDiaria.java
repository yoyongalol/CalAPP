package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntradaDiaria {
    private int id;
    private LocalDate fecha;
    private Usuario usuario;
    private List<AlimentoEntrada> alimentos = new ArrayList<>();

    public EntradaDiaria() {
    }

    public EntradaDiaria(int id, LocalDate fecha, Usuario usuario) {
        this.id = id;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    // Getters y setters
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<AlimentoEntrada> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<AlimentoEntrada> alimentos) {
        this.alimentos = alimentos;
    }

    // Método para calcular calorías totales del día (sumiendo que Alimento tiene getCalorias())
    public int getCaloriasTotales() {
        return alimentos.stream()
                .mapToInt(ae -> (ae.getAlimento().getCalorias() * ae.getCantidad()) / 100)
                .sum();
    }
}

