package com.aluracurso.biblioteca.repository;

import com.aluracurso.biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT DISTINCT a FROM Autor a WHERE a.fechaDeDefuncion < :fechaDeDefuncion")
    List<Autor> listarAutoresPorFecha(@Param("fechaDeDefuncion") int fechaDeDefuncion);

//    @Query("SELECT a.id, a.nombre FROM autor a JOIN libro_autor b ON a.id = b.autor_id GROUP BY a.id, a.nombre")
//    List<Autor> listarAutoresLibros();

    Optional<Autor> findByNombre(String nombre);
}

