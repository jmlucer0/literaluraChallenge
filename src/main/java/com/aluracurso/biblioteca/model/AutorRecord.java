package com.aluracurso.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorRecord(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNacimientpo,
        @JsonAlias("death_year") Integer fechaDefuncion
) {


}
