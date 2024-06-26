package com.aluracurso.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroRecord(
        @JsonAlias("title") String titulo,
        @JsonAlias("author")List<AutorRecord> autores,
        @JsonAlias("lenguages")List<String> idioma,
        @JsonAlias("download_count") Integer cantidadDeDescargas
        ) {
}
