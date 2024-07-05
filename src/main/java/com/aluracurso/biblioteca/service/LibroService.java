package com.aluracurso.biblioteca.service;

import com.aluracurso.biblioteca.model.Libro;
import com.aluracurso.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public void guardarLibro(Libro libro){
        try{
            libroRepository.save(libro);
        }catch (NullPointerException e){
            throw new NullPointerException();
        }
    }


}
