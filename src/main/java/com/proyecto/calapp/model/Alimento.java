package com.proyecto.calapp.model;

import java.util.Objects;
public class Alimento {

    private int idAlimento;
    private String nombreAlimento;
    private int calorias;
    private double proteinas;
    private double grasas;
    private double carbohidratos;
    private String categoria;

    public Alimento() {}

    public Alimento(String nombre, int calorias, double proteinas, double grasas, double carbohidratos, String categoria) {
        this.nombreAlimento = nombre;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
        this.categoria = categoria;
    }


    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNombreAlimento() {
        return nombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        this.nombreAlimento = nombreAlimento;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }

    public double getGrasas() {
        return grasas;
    }

    public void setGrasas(double grasas) {
        this.grasas = grasas;
    }

    public double getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "idAlimento=" + idAlimento +
                ", nombreAlimento='" + nombreAlimento + '\'' +
                ", calorias=" + calorias +
                ", proteinas=" + proteinas +
                ", grasas=" + grasas +
                ", carbohidratos=" + carbohidratos +
                ", categoria='" + categoria + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alimento alimento = (Alimento) o;
        return idAlimento == alimento.idAlimento && calorias == alimento.calorias && Double.compare(alimento.proteinas, proteinas) == 0 && Double.compare(alimento.grasas, grasas) == 0 && Double.compare(alimento.carbohidratos, carbohidratos) == 0 && Objects.equals(nombreAlimento, alimento.nombreAlimento) && Objects.equals(categoria, alimento.categoria);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idAlimento, nombreAlimento, calorias, proteinas, grasas, carbohidratos, categoria);
    }
}
