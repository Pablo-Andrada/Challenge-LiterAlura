// src/main/java/com/aluracursos/challenge/repository/AuthorRepository.java
package com.aluracursos.challenge.repository;

import com.aluracursos.challenge.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Lista todos los autores (este método no es necesario definirlo,
     * porque JpaRepository ya lo provee, pero lo dejamos por claridad).
     */
    List<Author> findAll();

    /**
     * Encuentra autores vivos en un determinado año:
     *   birthYear <= :year AND (deathYear IS NULL OR deathYear >= :year)
     */
    @Query("SELECT a FROM Author a " +
            "WHERE a.birthYear <= :year " +
            "  AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Author> findVivosEnAno(@Param("year") int year);
}
