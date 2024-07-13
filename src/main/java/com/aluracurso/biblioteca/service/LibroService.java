package com.aluracurso.biblioteca.service;

import com.aluracurso.biblioteca.entity.Autor;
import com.aluracurso.biblioteca.entity.Libro;
import com.aluracurso.biblioteca.model.DatosAPI;
import com.aluracurso.biblioteca.model.LibroRecord;
import com.aluracurso.biblioteca.repository.AutorRepository;
import com.aluracurso.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    String BASE_URL = "https://gutendex.com/books/";
    ConsumirAPI consumirAPI = new ConsumirAPI();
    ConvertirDatos convertirDatos = new ConvertirDatos();

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }


    public void buscarLibro(String libro) throws UnsupportedEncodingException {
        String libroBuscado = libro.replace(" ", "%20");
        String searchURL = BASE_URL + "?search=" + libroBuscado ;
        var json = consumirAPI.obtenerJson(searchURL);
        DatosAPI datos = convertirDatos.obtenerDatos(json, DatosAPI.class);

        Optional<LibroRecord> resultadoBusqueda = datos.resultados().stream().findFirst();
        Libro libroEncontrado = resultadoBusqueda.map(this::libroRecordALibro).orElse(null);
        if (libroEncontrado == null){
            System.out.println("No se encontro ningun libro con ese nombre \n");
        }else{
            System.out.println(libroEncontrado);
           if (validarTitulo(libroEncontrado)){
               System.out.println("El libro ya esta registrado");
           }else{
                libroEncontrado.setAutor(existeAutor(libroEncontrado.getAutor()));
               guardarLibro(libroEncontrado);
               System.out.println("Libro Registrado \n");
           }

        }
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

    public boolean validarTitulo(Libro libro){
         return libroRepository.existsByTitulo(libro.getTitulo());
    }

    public Autor buscarAutor(Autor autor){
       return autorRepository.findByNombre(autor.getNombre()).orElse(null);
    }

    public List<Autor> existeAutor(List<Autor> autores){
        return autores.stream().map(autor -> actualizarAutor(autor)).toList();
    }

    public Autor actualizarAutor(Autor autorViejo){
        Autor autorEnBaseDeDatos = buscarAutor(autorViejo);
        if (autorEnBaseDeDatos != null){
            return autorEnBaseDeDatos;
        }
        return autorRepository.save(autorViejo);
    }

}
