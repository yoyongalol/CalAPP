package com.proyecto.calapp.model;
import com.proyecto.calapp.model.Usuario;
import java.util.Objects;
public class Alimento {

    private int id;
    private String nombre;
    private int calorias;
    private double proteinas;
    private double grasas;
    private double carbohidratos;
    private String categoria;
    private String emailAdmin;
    private Usuario usuario;

    public Alimento() {}

    public Alimento(String nombre, int calorias, double proteinas, double grasas, double carbohidratos, String categoria, Usuario usuario) {
        this.nombre = nombre;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "idAlimento=" + id +
                ", nombreAlimento='" + nombre + '\'' +
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
        return id == alimento.id && calorias == alimento.calorias && Double.compare(alimento.proteinas, proteinas) == 0 && Double.compare(alimento.grasas, grasas) == 0 && Double.compare(alimento.carbohidratos, carbohidratos) == 0 && Objects.equals(nombre, alimento.nombre) && Objects.equals(categoria, alimento.categoria);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, calorias, proteinas, grasas, carbohidratos, categoria);
    }
}
