package com.aluracurso.biblioteca;

import com.aluracurso.biblioteca.model.*;
import com.aluracurso.biblioteca.service.ConsumirAPI;
import com.aluracurso.biblioteca.service.ConvertirDatos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Principal {
    String BASE_URL = "https://gutendex.com/books/";
    ConsumirAPI consumirAPI = new ConsumirAPI();
    ConvertirDatos convertirDatos = new ConvertirDatos();


    public Libro buscarLibro(String libro) throws UnsupportedEncodingException {
        String libroBuscado = URLEncoder.encode(libro, "UTF-8");

        String searchURL = BASE_URL + "?search=" + libroBuscado ;
        var json = consumirAPI.obtenerJson(searchURL);
        DatosAPI datos = convertirDatos.obtenerDatos(json, DatosAPI.class);

       Optional<LibroRecord> primerLibro = datos.resultados().stream().findFirst();
        Libro libro1 = primerLibro.map(this::libroRecordALibro).orElse(null);

        System.out.println(libro1);
        return libro1;
    }

    public Libro libroRecordALibro(LibroRecord libroRecord){
        Libro libro = new Libro();
        libro.setTitulo(libroRecord.titulo());
        libro.setIdioma(libroRecord.idioma().get(0));
        libro.setCantidadDeDescargas(libroRecord.cantidadDeDescargas());
        libro.setAutor(autorRecordAAutor(libroRecord.autores()));
        return libro;
    }

    public List<Autor> autorRecordAAutor(List<AutorRecord> autorRecord){

        return autorRecord.stream()
                .map(autores -> new Autor(autores.nombre(), autores.fechaNacimientpo(), autores.fechaDefuncion()))
                .collect(Collectors.toList());
    }
}
