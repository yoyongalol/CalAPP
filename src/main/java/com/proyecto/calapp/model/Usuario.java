package model;

import java.util.List;
import java.util.Objects;

public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private int edad;
    private double peso;
    private double altura;
    private int objetivoCalorias;

    public Usuario () {}

    public Usuario(int idUsuario, String nombreUsuario, int edad, double peso, double altura, int objetivoCalorias) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.objetivoCalorias = objetivoCalorias;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getObjetivoCalorias() {
        return objetivoCalorias;
    }

    public void setObjetivoCalorias(int objetivoCalorias) {
        this.objetivoCalorias = objetivoCalorias;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", altura=" + altura +
                ", objetivoCalorias=" + objetivoCalorias +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario && edad == usuario.edad && Double.compare(usuario.peso, peso) == 0 && Double.compare(usuario.altura, altura) == 0 && objetivoCalorias == usuario.objetivoCalorias && Objects.equals(nombreUsuario, usuario.nombreUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreUsuario, edad, peso, altura, objetivoCalorias);
    }

}
