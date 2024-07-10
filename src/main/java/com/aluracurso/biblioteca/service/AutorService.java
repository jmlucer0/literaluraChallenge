package com.aluracurso.biblioteca.service;

import com.aluracurso.biblioteca.model.Autor;
import com.aluracurso.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public void guardarAutor(Autor autor){
        try{
            autorRepository.save(autor);
        }catch (NullPointerException e){
            throw new NullPointerException();
        }
    }
}
