package com.aluracurso.biblioteca.entity;

import com.aluracurso.biblioteca.model.LibroRecord;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autor;
    @Column
    private String titulo;
    @Column
    private String idioma;
    @Column
    private Integer cantidadDeDescargas;

    public Libro() {

    }

    public Libro(String titulo, List<Autor> autor, String idioma, Integer cantidadDeDescargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.cantidadDeDescargas = cantidadDeDescargas;
    }

    public Libro(LibroRecord libroRecord){
        this.titulo = libroRecord.titulo();
        this.autor = libroRecord.autores().stream().map(autores -> new Autor(autores.nombre(), autores.fechaNacimientpo(), autores.fechaDefuncion()))
                .collect(Collectors.toList());
        this.idioma = libroRecord.idioma().get(0);
        this.cantidadDeDescargas = libroRecord.cantidadDeDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getCantidadDeDescargas() {
        return cantidadDeDescargas;
    }

    public void setCantidadDeDescargas(Integer cantidadDeDescargas) {
        this.cantidadDeDescargas = cantidadDeDescargas;

    }

    @Override
    public String toString() {
        return "-------------------\n" +
                "Libro: \n" +
                "Titulo='" + titulo + "'\n" +
                autor + "'\n" +
                "Idioma='" + idioma + "'\n" +
                "CantidadDeDescargas= " + cantidadDeDescargas + "\n";
    }
}
