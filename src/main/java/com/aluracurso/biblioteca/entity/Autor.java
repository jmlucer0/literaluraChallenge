package com.aluracurso.biblioteca.entity;

import com.aluracurso.biblioteca.model.AutorRecord;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
    private Integer fechaDeNacimeinto;
    private Integer fechaDeDefuncion;

    public Autor() {
    }

    public Autor(Long id, String nombre, Integer fechaDeNacimeinto, Integer fechaDeDefuncion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaDeNacimeinto = fechaDeNacimeinto;
        this.fechaDeDefuncion = fechaDeDefuncion;
    }
    public Autor(AutorRecord autorRecord){
        this.nombre = autorRecord.nombre();
        this.fechaDeNacimeinto = autorRecord.fechaNacimientpo();
        this.fechaDeDefuncion = autorRecord.fechaDefuncion();
    }

    public Autor(String nombre, Integer fechaDeNacimeinto, Integer fechaDeDefuncion) {
        this.nombre = nombre;
        this.fechaDeNacimeinto = fechaDeNacimeinto;
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimeinto() {
        return fechaDeNacimeinto;
    }

    public void setFechaDeNacimeinto(Integer fechaDeNacimeinto) {
        this.fechaDeNacimeinto = fechaDeNacimeinto;
    }

    public Integer getFechaDeDefuncion() {
        return fechaDeDefuncion;
    }

    public void setFechaDeDefuncion(Integer fechaDeDefuncion) {
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    @Override
    public String toString() {
        return
                "Autor:\n" +
                "  Nombre: " + nombre + '\n' +
                "  Fecha de nacimiento: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaDeNacimeinto) + '\n' +
                "  Fecha de defunción: " + (fechaDeDefuncion != null ? new SimpleDateFormat("dd/MM/yyyy").format(fechaDeDefuncion) : "Desconocida") + '\n';
    }
}
