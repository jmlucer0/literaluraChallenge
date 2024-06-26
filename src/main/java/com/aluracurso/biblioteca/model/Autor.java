package com.aluracurso.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String fechaDeNacimeinto;
    private String fechaDeDefuncion;

    public Autor() {
    }

    public Autor(String nombre, String fechaDeNacimeinto, String fechaDeDefuncion) {
        this.nombre = nombre;
        this.fechaDeNacimeinto = fechaDeNacimeinto;
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimeinto() {
        return fechaDeNacimeinto;
    }

    public void setFechaDeNacimeinto(String fechaDeNacimeinto) {
        this.fechaDeNacimeinto = fechaDeNacimeinto;
    }

    public String getFechaDeDefuncion() {
        return fechaDeDefuncion;
    }

    public void setFechaDeDefuncion(String fechaDeDefuncion) {
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", fechaDeNacimeinto='" + fechaDeNacimeinto + '\'' +
                ", fechaDeDefuncion='" + fechaDeDefuncion + '\'' +
                '}';
    }
}
