package com.proyecto.calapp.model;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class AlimentoUsuario {

    private Usuario usuario;
    private Alimento alimento;
    private Date fecha;
    private int cantidad;

    public AlimentoUsuario() {}

    public AlimentoUsuario(Usuario usuario, Alimento alimento, Date fecha, int cantidad) {
        this.usuario = usuario;
        this.alimento = alimento;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "AlimentoUsuario{" +
                "usuario=" + usuario +
                ", alimentos=" + alimento +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlimentoUsuario)) return false;

        AlimentoUsuario that = (AlimentoUsuario) o;

        if (cantidad != that.cantidad) return false;
        if (!usuario.equals(that.usuario)) return false;
        if (!alimento.equals(that.alimento)) return false;
        return fecha.equals(that.fecha);
    }
}



