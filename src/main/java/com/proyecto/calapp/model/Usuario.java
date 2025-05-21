package com.proyecto.calapp.model;

import java.util.Objects;

public class Usuario {

    private String email;
    private String contraseña;
    private String nombreUsuario;
    private int edad;
    private double peso;
    private double altura;
    private int objetivoCalorias;
    private String emailAnterior;

    public Usuario () {}

    public Usuario(String email, String contraseña, String nombreUsuario, int edad, double peso, double altura, int objetivoCalorias) {
        this.email = email;
        this.contraseña = contraseña;
        this.nombreUsuario = nombreUsuario;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.objetivoCalorias = objetivoCalorias;
        this.emailAnterior = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public String getEmailAnterior() {
        return emailAnterior;
    }

    public void setEmailAnterior(String emailAnterior) {
        this.emailAnterior = emailAnterior;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", altura=" + altura +
                ", objetivoCalorias=" + objetivoCalorias +
                ", emailAnterior='" + emailAnterior + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return edad == usuario.edad &&
                Double.compare(usuario.peso, peso) == 0 &&
                Double.compare(usuario.altura, altura) == 0 &&
                objetivoCalorias == usuario.objetivoCalorias &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(contraseña, usuario.contraseña) &&
                Objects.equals(nombreUsuario, usuario.nombreUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email,contraseña ,nombreUsuario, edad, peso, altura, objetivoCalorias);
    }

}
