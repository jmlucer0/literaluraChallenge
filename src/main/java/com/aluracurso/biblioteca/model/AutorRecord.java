package com.aluracurso.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorRecord(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaDeNacimientpo,
        @JsonAlias("death_year") Integer fechaDeDefuncion
) {
}
