package com.aluracurso.biblioteca.service;

import com.aluracurso.biblioteca.entity.Libro;
import com.aluracurso.biblioteca.model.DatosAPI;
import com.aluracurso.biblioteca.model.LibroRecord;
import com.aluracurso.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;


    String BASE_URL = "https://gutendex.com/books/";
    ConsumirAPI consumirAPI = new ConsumirAPI();
    ConvertirDatos convertirDatos = new ConvertirDatos();

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void buscarLibro(String libro) throws UnsupportedEncodingException {
        String libroBuscado = URLEncoder.encode(libro, "UTF-8");
        String searchURL = BASE_URL + "?search=" + libroBuscado ;
        var json = consumirAPI.obtenerJson(searchURL);
        DatosAPI datos = convertirDatos.obtenerDatos(json, DatosAPI.class);

        Optional<LibroRecord> resultadoBusqueda = datos.resultados().stream().findFirst();
        Libro libroEncontrado = resultadoBusqueda.map(this::libroRecordALibro).orElse(null);

        System.out.println(libroEncontrado);
        guardarLibro(libroEncontrado);
    }

    public Libro libroRecordALibro(LibroRecord libroRecord){
        Libro libro = new Libro(libroRecord);
        return libro;
    }


    public void guardarLibro(Libro libro){
        try{
            libroRepository.save(libro);
        }catch (NullPointerException e){
            throw new NullPointerException();
        }
    }

    public void listarLibros(){
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }


    public void listarLibrosIdioma(String idioma){
        if (idioma.equalsIgnoreCase("otro")){
            List<String> titulos = libroRepository.listarLibrosPorIdioma(idioma);
            if (titulos.isEmpty()){
                System.out.println("No hay libros regitrados con ese idioma");
            }else{
                titulos.forEach(System.out::println);
            }
        }
        List<String> titulos = libroRepository.listarLibrosPorIdioma(idioma);
        if (titulos.isEmpty()){
            System.out.println("No hay libros regitrados con ese idioma");
        }else{
            titulos.forEach(System.out::println);
        }
    }
}
